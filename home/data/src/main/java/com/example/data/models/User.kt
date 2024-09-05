package com.example.data.models

import com.example.data.database.models.UserDB
import com.example.domain.models.UserPresentation

data class User(
    val id: String,
    val username: String,
    val name: String? = null,
    val bio: String? = null,
)

fun User.toUserPresentation() =
    UserPresentation(
        id,
        username,
        name,
        bio,
    )

fun User.toUserDB() =
    UserDB(
        id,
        username,
        name,
        bio,
    )
