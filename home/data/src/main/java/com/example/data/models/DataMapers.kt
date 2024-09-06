package com.example.data.models

import com.example.data.database.models.NewPhotoDetail
import com.example.data.database.models.PopularPhotoDetail
import com.example.data.ext.toDateTime
import com.example.domain.models.PhotoDetail

fun NewPhotoDetail.toPhotoDetail() =
    PhotoDetail(
        id = this.newPhoto.id,
        url = this.newPhoto.url,
        createDataTime = this.newPhoto.createDataTime.toDateTime(),
        description = this.newPhoto.description,
        username = this.user.username,
        name = this.user.name,
        bio = this.user.bio,
    )

fun PopularPhotoDetail.toPhotoDetail() =
    PhotoDetail(
        id = this.popularPhoto.id,
        url = this.popularPhoto.url,
        createDataTime = this.popularPhoto.createDataTime.toDateTime(),
        description = this.popularPhoto.description,
        username = this.user.username,
        name = this.user.name,
        bio = this.user.bio,
    )
