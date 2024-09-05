package com.example.domain.models

data class UserPresentation(
    val id: String,
    val username: String,
    val name: String? = null,
    val bio: String? = null,
)
