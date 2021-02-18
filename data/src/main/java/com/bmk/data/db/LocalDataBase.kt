package com.bmk.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [InputData::class], version = 1)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun matchDOA(): LocalDOA
}