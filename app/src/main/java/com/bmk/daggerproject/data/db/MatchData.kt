package com.bmk.daggerproject.data.db

import androidx.room.*
import io.reactivex.Observable

@Entity(tableName = "match_data")
data class MatchData(
    @PrimaryKey val id: String,
    val title: String,
    val imgUrl: String,
    val comment: String = ""
)

@Dao
interface MatchDOA {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<MatchData>)

    @Query("SELECT * FROM match_data where id =:param LIMIT 1")
    fun getData(param: String): Observable<MatchData>
}