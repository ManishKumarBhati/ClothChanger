package com.bmk.daggerproject.domain

import io.reactivex.Observable
import io.reactivex.Single

interface MatchRepository {
    fun getMatchData(): Observable<List<ResponseData>>
    fun getTeam(): Single<String>
}