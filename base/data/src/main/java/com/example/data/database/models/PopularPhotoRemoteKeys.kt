package com.example.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.database.contracts.PopularPhotoRemoteKeysContracts

@Entity(tableName = PopularPhotoRemoteKeysContracts.TABLE_NAME)
data class PopularPhotoRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = PopularPhotoRemoteKeysContracts.Colums.ID)
    val id: String,
    @ColumnInfo(name = PopularPhotoRemoteKeysContracts.Colums.PREV_PAGE)
    val prevPage: Int?,
    @ColumnInfo(name = PopularPhotoRemoteKeysContracts.Colums.NEXT_PAGE)
    val nextPage: Int?,
)
