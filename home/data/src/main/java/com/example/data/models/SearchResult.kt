package com.example.data.models

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("results")
    val images: List<Photo>,
)
