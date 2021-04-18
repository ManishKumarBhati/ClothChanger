package com.bmk.domain

import android.os.Parcelable

data class DataRequest(
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

data class DataResponse(
    @JvmField val id: Long,
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

data class KeyValue(
    @JvmField val id: Int,
    @JvmField val value: String
) {
    override fun toString(): String {
        return value
    }
}

data class PersonalData(
    @JvmField val fName: String,
    @JvmField val lName: String,
    @JvmField val mob: String,
    @JvmField val gender: String,
    @JvmField val dob: String
)


data class EmployeeData(
    @JvmField val empNo: String,
    @JvmField val empName: String,
    @JvmField val empdesg: String,
    @JvmField val accountType: String,
    @JvmField val exp: String
)

data class BankData(
    @JvmField val bankName: String,
    @JvmField val branch: String,
    @JvmField val acNo: String,
    @JvmField val ifscCode: String,
    @JvmField val image: String
)

data class UserData(
    @JvmField val id: Long,
    @JvmField val image: String,
    @JvmField val topbottom: Int
)