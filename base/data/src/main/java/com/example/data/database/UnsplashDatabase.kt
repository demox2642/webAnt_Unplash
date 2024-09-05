package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.dao.NewPhotoDAO
import com.example.data.database.dao.NewPhotoRemoteDAO
import com.example.data.database.dao.PopularPhotoDAO
import com.example.data.database.dao.PopularPhotoRemoteDAO
import com.example.data.database.dao.UserDAO
import com.example.data.database.models.NewPhotoDB
import com.example.data.database.models.NewPhotoRemoteKeys
import com.example.data.database.models.PopularPhotoDB
import com.example.data.database.models.PopularPhotoRemoteKeys
import com.example.data.database.models.UserDB

@Database(
    entities = [

        NewPhotoDB::class,
        NewPhotoRemoteKeys::class,
        PopularPhotoDB::class,
        PopularPhotoRemoteKeys::class,
        UserDB::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class UnsplashDatabase : RoomDatabase() {
    abstract fun newPhotoDao(): NewPhotoDAO

    abstract fun newPhotoRemoteKeyDao(): NewPhotoRemoteDAO

    abstract fun popularPhotoDao(): PopularPhotoDAO

    abstract fun popularPhotoRemoteKeyDao(): PopularPhotoRemoteDAO

    abstract fun userDao(): UserDAO
}
