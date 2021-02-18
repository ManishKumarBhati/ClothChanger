package com.bmk.domain

import io.reactivex.Observable
import io.reactivex.Single

interface MatchRepository {
    fun getData(): Observable<List<DataResponse>>
    fun saveData(request: DataRequest): Single<Long>
}