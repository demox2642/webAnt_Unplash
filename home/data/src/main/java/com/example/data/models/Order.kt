package com.example.data.models

enum class Order(
    val order: String,
) {
    LATEST("latest"),
    OLDEST("oldest"),
    POPULAR("popular"),
}
