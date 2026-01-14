package com.example.moodupdate

import android.content.Context
import android.provider.Settings
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import java.time.Duration
import java.time.Instant

class DataSyncWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    private val healthConnectManager = HealthConnectManager(context)
    private val patientId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    override suspend fun doWork(): Result {
        return try {
            if (!healthConnectManager.hasAllPermissions()) {
                return Result.retry()
            }

            // Sync Steps (Total of last hour)
            val stepsCount = healthConnectManager.readStepsLastHour()
            SupabaseManager.uploadSteps(
                StepData(
                    patient_id = patientId,
                    timestamp = Instant.now().toString(),
                    count = stepsCount
                )
            )

            // Sync Sleep (Sessions in last 24h)
            val sleepSessions = healthConnectManager.readSleepSessionsLast24Hours()
            for (session in sleepSessions) {
                val duration = Duration.between(session.startTime, session.endTime).toMinutes()
                SupabaseManager.uploadSleep(
                    SleepData(
                        patient_id = patientId,
                        start_time = session.startTime.toString(),
                        end_time = session.endTime.toString(),
                        duration_minutes = duration
                    )
                )
            }

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
}