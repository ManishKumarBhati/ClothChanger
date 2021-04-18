package com.bmk.data.db

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Entity(tableName = "user_info_data")
data class InputData(
    @PrimaryKey val id: Long,
    @JvmField val fName: String,
    @JvmField val lName: String,
    @JvmField val mob: String,
    @JvmField val gender: String,
    @JvmField val dob: String,
    @JvmField val empNo: String,
    @JvmField val empName: String,
    @JvmField val empdesg: String,
    @JvmField val accountType: String,
    @JvmField val exp: String,
    @JvmField val bankName: String,
    @JvmField val branch: String,
    @JvmField val acNo: String,
    @JvmField val ifscCode: String,
    @JvmField val image: String

)

@Entity(tableName = "user_cloth_data")
data class UserClothData(
    @PrimaryKey val id: Long,
    @JvmField val image: String,
    @JvmField val topbottom: Int,
    @JvmField val isFav: Boolean = false
)

@Dao
interface LocalDOA {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: InputData): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: UserClothData): Long

    @Query("SELECT * FROM user_info_data where id =:id LIMIT 1")
    fun getData(id: Long): Observable<InputData>

    @Query("SELECT * FROM user_info_data ")
    fun getAllData(): Observable<List<InputData>>

    @Query("SELECT * FROM user_cloth_data ")
    fun getUserData(): Observable<List<UserClothData>>

    @Query(
        "Update user_info_data set fName=:fName,lName=:lName,mob=:mob,gender=:gender,dob=:dob,empNo=:empNo,empName=:empName,empdesg=:empdesg,accountType=:accountType,exp=:exp ,bankName=:bankName,branch=:branch,acNo=:acNo,ifscCode=:ifscCode,image=:image where id =:id "
    )
    fun updateData(
        id: Long,
        fName: String,
        lName: String,
        mob: String,
        gender: String,
        dob: String,
        empNo: String,
        empName: String,
        empdesg: String,
        accountType: String,
        exp: String,
        bankName: String,
        branch: String,
        acNo: String,
        ifscCode: String,
        image: String
    ): Single<Int>


}