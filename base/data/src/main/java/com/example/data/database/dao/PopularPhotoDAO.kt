package com.example.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.contracts.PopularPhotoContracts
import com.example.data.database.models.PopularPhotoDB
import com.example.data.database.models.PopularPhotoDetail

@Dao
interface PopularPhotoDAO {
    @Query("SELECT * FROM ${PopularPhotoContracts.TABLE_NAME}")
    fun getAllImages(): PagingSource<Int, PopularPhotoDB>

    @Query("SELECT * FROM ${PopularPhotoContracts.TABLE_NAME} WHERE ${PopularPhotoContracts.Colums.ID} =:id")
    suspend fun getPopularPhoto(id: String): PopularPhotoDetail

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllPopularPhoto(popularPhotoList: List<PopularPhotoDB>)

    @Query("DELETE FROM ${PopularPhotoContracts.TABLE_NAME}")
    suspend fun deleteAllPopularPhoto()
}
