package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.contracts.UserContracts
import com.example.data.database.models.UserDB

@Dao
interface UserDAO {
    @Query("SELECT * FROM ${UserContracts.TABLE_NAME} WHERE ${UserContracts.Colums.ID} =:id")
    suspend fun getUser(id: String): UserDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllUser(userList: List<UserDB>)

    @Query("DELETE FROM ${UserContracts.TABLE_NAME}")
    suspend fun deleteAllUser()
}
