package com.example.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.database.contracts.NewPhotoRemoteKeysContracts

@Entity(tableName = NewPhotoRemoteKeysContracts.TABLE_NAME)
data class NewPhotoRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = NewPhotoRemoteKeysContracts.Colums.ID)
    val id: String,
    @ColumnInfo(name = NewPhotoRemoteKeysContracts.Colums.PREV_PAGE)
    val prevPage: Int?,
    @ColumnInfo(name = NewPhotoRemoteKeysContracts.Colums.NEXT_PAGE)
    val nextPage: Int?,
)
