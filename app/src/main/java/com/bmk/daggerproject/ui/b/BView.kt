package com.bmk.daggerproject.ui.b

import com.bmk.daggerproject.util.base.BaseView
import com.bmk.domain.DetailsData
import io.reactivex.Observable

interface BView : BaseView {
    fun getFirstName(): String
    fun getLastName(): String
    fun getMob(): String
    fun getGender(): String
    fun getDOB(): String
    fun onDobClick(): Observable<Unit>
    fun showDatePicker()
    fun onSubmitClick(): Observable<Unit>
    fun showError(error: String)
    fun empScreen()
}
