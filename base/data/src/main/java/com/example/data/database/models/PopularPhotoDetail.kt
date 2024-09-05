package com.example.data.database.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.data.database.contracts.PopularPhotoContracts
import com.example.data.database.contracts.UserContracts

data class PopularPhotoDetail(
    @Embedded
    val popularPhoto: PopularPhotoDB,
    @Relation(
        parentColumn = PopularPhotoContracts.Colums.USER_ID,
        entityColumn = UserContracts.Colums.ID,
    )
    val user: UserDB,
)
