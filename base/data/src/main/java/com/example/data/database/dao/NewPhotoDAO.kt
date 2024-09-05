package com.example.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.contracts.NewPhotoContracts
import com.example.data.database.models.NewPhotoDB
import com.example.data.database.models.NewPhotoDetail

@Dao
interface NewPhotoDAO {
    @Query("SELECT * FROM ${NewPhotoContracts.TABLE_NAME}")
    fun getAllImages(): PagingSource<Int, NewPhotoDB>

    @Query("SELECT * FROM ${NewPhotoContracts.TABLE_NAME} WHERE ${NewPhotoContracts.Colums.ID} =:id")
    fun getNewPhoto(id: String): NewPhotoDetail

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllNewPhoto(newPhotoList: List<NewPhotoDB>)

    @Query("DELETE FROM ${NewPhotoContracts.TABLE_NAME}")
    fun deleteNewPhoto()
}
