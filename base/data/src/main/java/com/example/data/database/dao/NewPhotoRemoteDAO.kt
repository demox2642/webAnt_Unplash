package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.contracts.NewPhotoRemoteKeysContracts
import com.example.data.database.contracts.UserContracts
import com.example.data.database.models.NewPhotoRemoteKeys

@Dao
interface NewPhotoRemoteDAO {
    @Query("SELECT * FROM ${NewPhotoRemoteKeysContracts.TABLE_NAME} WHERE ${NewPhotoRemoteKeysContracts.Colums.ID} =:id")
    fun getNewPhotoRemote(id: String): NewPhotoRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllNewPhotoRemote(remoteKeys: List<NewPhotoRemoteKeys>)

    @Query("DELETE FROM ${UserContracts.TABLE_NAME}")
    fun deleteAllNewPhotoRemote()
}
