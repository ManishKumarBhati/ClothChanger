package com.bmk.domain

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
    @JvmField val id: String,
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