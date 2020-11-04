package com.bmk.daggerproject.domain

import io.reactivex.Observable
import io.reactivex.Single

interface MatchRepository {
    fun getMatchData(): Observable<List<ResponseData>>
    fun getData(id: String): Observable<DetailsData>
    fun saveComment(id: String, comment: String): Single<Int>
}