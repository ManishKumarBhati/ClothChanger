package com.bmk.daggerproject.ui.d

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonalInputRequest(
    @JvmField val fName: String,
    @JvmField val lName: String,
    @JvmField val mob: String,
    @JvmField val gender: String,
    @JvmField val dob: String
) : Parcelable

@Parcelize
data class EmployeeInputRequest(
    @JvmField val personalInfo: PersonalInputRequest,
    @JvmField val empNo: String,
    @JvmField val empName: String,
    @JvmField val empdesg: String,
    @JvmField val accountType: String,
    @JvmField val exp: String
) : Parcelable