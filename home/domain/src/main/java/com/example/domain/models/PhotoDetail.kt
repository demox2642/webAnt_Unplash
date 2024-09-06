package com.example.domain.models

import java.time.LocalDateTime

data class PhotoDetail(
    val id: String,
    val url: String,
    val createDataTime: LocalDateTime,
    val description: String? = null,
    val username: String,
    val name: String? = null,
    val bio: String? = null,
)
