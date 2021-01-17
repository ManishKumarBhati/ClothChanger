package com.bmk.domain

import io.reactivex.Observable
import io.reactivex.Single

interface MatchRepository {
    fun getMatchData(): Observable<List<ResponseData>>
    fun getMatchData(query: String): Observable<List<ResponseData>>
    fun getData(id: String): Observable<DetailsData>
    fun saveComment(id: String, comment: String): Single<Int>
}