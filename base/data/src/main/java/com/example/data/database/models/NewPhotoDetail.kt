package com.example.data.database.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.data.database.contracts.NewPhotoContracts
import com.example.data.database.contracts.UserContracts

data class NewPhotoDetail(
    @Embedded
    val newPhoto: NewPhotoDB,
    @Relation(
        parentColumn = NewPhotoContracts.Colums.USER_ID,
        entityColumn = UserContracts.Colums.ID,
    )
    val user: UserDB,
)
