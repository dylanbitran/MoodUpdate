package com.example.moodupdate

import io.github.jan_tennert.supabase.SupabaseClient
import io.github.jan_tennert.supabase.createSupabaseClient
import io.github.jan_tennert.supabase.postgrest.Postgrest
import io.github.jan_tennert.supabase.postgrest.postgrest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

object SupabaseManager {
    // REEMPLAZAR con tus credenciales de Supabase
    private const val SUPABASE_URL = "https://your-project.supabase.co"
    private const val SUPABASE_KEY = "your-anon-key"

    val client: SupabaseClient = createSupabaseClient(
        supabaseUrl = SUPABASE_URL,
        supabaseKey = SUPABASE_KEY
    ) {
        install(Postgrest)
    }

    suspend fun uploadSteps(stepData: StepData) {
        client.postgrest["steps"].insert(stepData)
    }

    suspend fun uploadSleep(sleepData: SleepData) {
        client.postgrest["sleep_sessions"].insert(sleepData)
    }
}