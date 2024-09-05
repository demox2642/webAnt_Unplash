package com.example.data.models

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.data.database.models.NewPhotoDB
import com.example.data.database.models.PopularPhotoDB
import com.example.data.ext.toDataTime
import com.example.domain.models.PhotoPresentation
import com.google.gson.annotations.SerializedName
import java.time.ZoneOffset

data class Photo(
    val id: String,
    @SerializedName("created_at")
    val createDataTime: String,
    val urls: Urls,
    val user: User,
    val description: String? = null,
)

@RequiresApi(Build.VERSION_CODES.O)
fun Photo.toPhotoPresentation() =
    PhotoPresentation(
        id = this.id,
        url = this.urls.small,
        createDataTime = this.createDataTime.toDataTime(),
        description = this.description,
    )

@RequiresApi(Build.VERSION_CODES.O)
fun Photo.toPhotoDB() =
    NewPhotoDB(
        id = this.id,
        url = this.urls.small,
        createDataTime = this.createDataTime.toDataTime().toEpochSecond(ZoneOffset.UTC),
        userId = this.user.id,
        description = this.description,
    )

@RequiresApi(Build.VERSION_CODES.O)
fun Photo.toPopularPhotoDB() =
    PopularPhotoDB(
        id = this.id,
        url = this.urls.small,
        createDataTime = this.createDataTime.toDataTime().toEpochSecond(ZoneOffset.UTC),
        userId = this.user.id,
        description = this.description,
    )
