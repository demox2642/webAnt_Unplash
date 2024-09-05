package com.example.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.data.database.contracts.PopularPhotoContracts
import com.example.data.database.contracts.UserContracts

@Entity(
    tableName = PopularPhotoContracts.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = UserDB::class,
            parentColumns = arrayOf(UserContracts.Colums.ID),
            childColumns = arrayOf(PopularPhotoContracts.Colums.USER_ID),
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class PopularPhotoDB(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = PopularPhotoContracts.Colums.ID)
    val id: String,
    @ColumnInfo(name = PopularPhotoContracts.Colums.USER_ID)
    val userId: String,
    @ColumnInfo(name = PopularPhotoContracts.Colums.URL)
    val url: String,
    @ColumnInfo(name = PopularPhotoContracts.Colums.CREATE_DATA_TIME)
    val createDataTime: Long,
    @ColumnInfo(name = PopularPhotoContracts.Colums.DESCRIPTION)
    val description: String? = null,
)
