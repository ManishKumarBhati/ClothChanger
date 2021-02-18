package com.bmk.daggerproject.ui.d

import android.widget.Toast
import com.bmk.daggerproject.util.base.BaseView
import com.bmk.domain.DetailsData
import com.bmk.domain.KeyValue
import io.reactivex.Observable

interface DView : BaseView {
    fun getBankName(): String
    fun getBranchName(): KeyValue
    fun getAcNo(): String
    fun getIfsc(): String
    fun onSubmitClick(): Observable<Unit>
    fun backToHome()
    fun showError(error: String)

}
