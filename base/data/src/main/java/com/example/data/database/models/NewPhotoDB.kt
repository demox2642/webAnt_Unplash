package com.example.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.data.database.contracts.NewPhotoContracts
import com.example.data.database.contracts.UserContracts

@Entity(
    tableName = NewPhotoContracts.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = UserDB::class,
            parentColumns = arrayOf(UserContracts.Colums.ID),
            childColumns = arrayOf(NewPhotoContracts.Colums.USER_ID),
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class NewPhotoDB(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = NewPhotoContracts.Colums.ID)
    val id: String,
    @ColumnInfo(name = NewPhotoContracts.Colums.USER_ID)
    val userId: String,
    @ColumnInfo(name = NewPhotoContracts.Colums.URL)
    val url: String,
    @ColumnInfo(name = NewPhotoContracts.Colums.CREATE_DATA_TIME)
    val createDataTime: Long,
    @ColumnInfo(name = NewPhotoContracts.Colums.DESCRIPTION)
    val description: String? = null,
) {
    fun toTestString() {
    }
}
