package com.example.moodupdate

import android.util.Log
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest

object SupabaseManager {
    private const val SUPABASE_URL = "https://jalnvorosccaqaedttec.supabase.co"
    
    // Clave anon public real (Confirmada)
    private const val SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImphbG52b3Jvc2NjYXFhZWR0dGVjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Njg0MTU4MzIsImV4cCI6MjA4Mzk5MTgzMn0.Haqj2olzs5QmbE7DZyXIj4yhHdhaTi8DPAkka-mYK6g"

    val client: SupabaseClient = createSupabaseClient(
        supabaseUrl = SUPABASE_URL,
        supabaseKey = SUPABASE_KEY
    ) {
        install(Postgrest)
    }

    suspend fun uploadSteps(stepData: StepData) {
        try {
            client.postgrest["steps"].insert(stepData)
            Log.d("Supabase", "Pasos subidos con éxito")
        } catch (e: Exception) {
            Log.e("Supabase", "Error subiendo pasos: ${e.message}", e)
        }
    }

    suspend fun uploadSleep(sleepData: SleepData) {
        try {
            client.postgrest["sleep_sessions"].insert(sleepData)
            Log.d("Supabase", "Sueño subido con éxito")
        } catch (e: Exception) {
            Log.e("Supabase", "Error subiendo sueño: ${e.message}", e)
        }
    }
}