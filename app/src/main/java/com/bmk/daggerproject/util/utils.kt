package com.bmk.daggerproject.util

import android.app.DatePickerDialog
import android.content.Context
import android.widget.ArrayAdapter
import com.bmk.daggerproject.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun Context.showDatePicker(
    callback: (Date) -> Unit,
    setDate: Date?,
    minDate: Date?,
    maxDate: Date?
): DatePickerDialog {
    val now = Calendar.getInstance()
    if (setDate != null) {
        now.time = setDate
    }
    val datePickerDialog = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->
        val cal = Calendar.getInstance()
        cal.clear()
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.MONTH, monthOfYear)
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        callback.invoke(cal.time)
    }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
    val datePicker = datePickerDialog.datePicker
    if (minDate != null) {
        datePicker.minDate = minDate.time
    }
    if (maxDate != null) {
        datePicker.maxDate = maxDate.time
    }
    datePickerDialog.show()
    return datePickerDialog
}

val dateFormat: DateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
