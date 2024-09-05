package com.example.domain.models

import java.time.LocalDateTime

data class PhotoPresentation(
    val id: String,
    val url: String,
    val createDataTime: LocalDateTime,
    val description: String? = null,
)
