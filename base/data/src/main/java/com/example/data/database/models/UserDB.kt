package com.example.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.database.contracts.UserContracts

@Entity(tableName = UserContracts.TABLE_NAME)
data class UserDB(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = UserContracts.Colums.ID)
    val id: String,
    @ColumnInfo(name = UserContracts.Colums.USER_NAME)
    val username: String,
    @ColumnInfo(name = UserContracts.Colums.NAME)
    val name: String? = null,
    @ColumnInfo(name = UserContracts.Colums.BIO)
    val bio: String? = null,
)
