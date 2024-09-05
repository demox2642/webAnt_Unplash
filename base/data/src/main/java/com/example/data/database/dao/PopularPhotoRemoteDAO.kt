package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.contracts.PopularPhotoRemoteKeysContracts
import com.example.data.database.models.PopularPhotoRemoteKeys

@Dao
interface PopularPhotoRemoteDAO {
    @Query("SELECT * FROM ${PopularPhotoRemoteKeysContracts.TABLE_NAME} WHERE ${PopularPhotoRemoteKeysContracts.Colums.ID} =:id")
    suspend fun getPopularPhotoKey(id: String): PopularPhotoRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllPopularPhotoKey(remoteKeys: List<PopularPhotoRemoteKeys>)

    @Query("DELETE FROM ${PopularPhotoRemoteKeysContracts.TABLE_NAME}")
    suspend fun deleteAllPopularPhotoKey()
}
