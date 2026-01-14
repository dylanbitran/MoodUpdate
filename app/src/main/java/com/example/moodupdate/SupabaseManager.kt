package com.example.moodupdate

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest

object SupabaseManager {
    // REEMPLAZAR con tus credenciales de Supabase
    private const val SUPABASE_URL = "https://jalnvorosccaqaedttec.supabase.co"
    private const val SUPABASE_KEY = "sb_publishable_DM_gLKTJA7k6y4yRNUBenA_RSezNb1B"

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