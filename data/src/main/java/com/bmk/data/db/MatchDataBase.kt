package com.bmk.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [InputData::class],
    version = 1, exportSchema = false
)
abstract class MatchDataBase : RoomDatabase() {
    abstract fun matchDOA(): MatchDOA
}