package com.bmk.domain

import io.reactivex.Observable
import io.reactivex.Single

interface MatchRepository {
    fun getData(): Observable<List<DataResponse>>
    fun saveData(request: DataRequest): Observable<Long>
    fun getPersonalData(id: Long): Observable<PersonalData>
    fun getEmpData(id: Long): Observable<EmployeeData>
    fun getBankData(id: Long): Observable<BankData>
    fun getModifyData(id: Long, request: DataRequest): Observable<Int>
    fun saveImage(url: String, id: Int): Observable<Long>
    fun getImageList(): Observable<List<UserData>>
}