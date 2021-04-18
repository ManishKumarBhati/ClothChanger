package com.bmk.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavData::class, UserClothData::class], version = 2)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun matchDOA(): LocalDOA
}