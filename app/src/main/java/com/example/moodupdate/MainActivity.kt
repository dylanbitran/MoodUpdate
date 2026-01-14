package com.example.moodupdate

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.health.connect.client.PermissionController
import androidx.lifecycle.lifecycleScope
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var healthConnectManager: HealthConnectManager
    private lateinit var statusText: TextView
    private lateinit var uidText: TextView

    private val requestPermissionActivityContract = PermissionController.createRequestPermissionResultContract()

    private val requestPermissions = registerForActivityResult(requestPermissionActivityContract) { granted ->
        if (granted.containsAll(healthConnectManager.permissions)) {
            updateUI(true)
            scheduleDataSync()
        } else {
            updateUI(false)
            Toast.makeText(this, "Permisos necesarios para funcionar", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        healthConnectManager = HealthConnectManager(this)
        statusText = findViewById(R.id.connectionStatus)
        uidText = findViewById(R.id.patientUid)

        val patientId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        uidText.text = patientId

        findViewById<Button>(R.id.copyUidButton).setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Patient UID", patientId)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "UID copiado", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.requestPermissionsButton).setOnClickListener {
            lifecycleScope.launch {
                requestPermissions.launch(healthConnectManager.permissions)
            }
        }

        checkPermissionsAndStatus()
    }

    private fun checkPermissionsAndStatus() {
        lifecycleScope.launch {
            val hasPerms = healthConnectManager.hasAllPermissions()
            updateUI(hasPerms)
            if (hasPerms) {
                scheduleDataSync()
            }
        }
    }

    private fun updateUI(isConnected: Boolean) {
        if (isConnected) {
            statusText.text = "Conectado y Sincronizando"
            statusText.setTextColor(getColor(android.R.color.holo_green_dark))
        } else {
            statusText.text = "Requiere Configuración"
            statusText.setTextColor(getColor(android.R.color.holo_red_dark))
        }
    }

    private fun scheduleDataSync() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        // El intervalo mínimo para PeriodicWork es 15 minutos
        val syncRequest = PeriodicWorkRequestBuilder<DataSyncWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "HealthDataSync",
            androidx.work.ExistingPeriodicWorkPolicy.KEEP,
            syncRequest
        )
    }
}