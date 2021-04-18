package com.bmk.data.db

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

@Entity(tableName = "user_cloth_data")
data class UserClothData(
    @PrimaryKey val id: Long,
    @JvmField val image: String,
    @JvmField val topbottom: Int
)

@Entity(tableName = "fav_data")
data class FavData(
    @PrimaryKey val id: Long,
    @JvmField val topId: Long,
    @JvmField val bottomId: Long,
    @JvmField val isFav: Boolean = false
)

@Dao
interface LocalDOA {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: UserClothData): Long

    @Query("SELECT * FROM user_cloth_data ")
    fun getUserData(): Observable<List<UserClothData>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: FavData): Long

    @Query("SELECT * FROM fav_data where topId =:topId AND bottomId =:bottomId LIMIT 1")
    fun getFavData(topId: Long, bottomId: Long): Single<FavData>

    @Query("SELECT * FROM fav_data where topId =:topId AND bottomId =:bottomId")
    fun getFavCount(topId: Long, bottomId: Long): Single<FavData>

    @Query("Update fav_data set isFav=:isFav where topId =:topId AND bottomId =:bottomId")
    fun updateData(isFav: Boolean, topId: Long, bottomId: Long): Single<Int>
}