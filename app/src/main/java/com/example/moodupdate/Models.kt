package com.example.moodupdate

import kotlinx.serialization.Serializable

@Serializable
data class StepData(
    val id: String? = null,
    val patient_id: String,
    val timestamp: String, // ISO8601
    val count: Long
)

@Serializable
data class SleepData(
    val id: String? = null,
    val patient_id: String,
    val start_time: String, // ISO8601
    val end_time: String,   // ISO8601
    val duration_minutes: Long
)