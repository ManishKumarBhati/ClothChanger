package com.bmk.daggerproject.ui.base


interface BaseView {
    fun showErrorMessage(error: String?)
    fun showProgress(show: Boolean)

}