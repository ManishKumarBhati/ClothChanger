package com.bmk.domain

import io.reactivex.Observable
import io.reactivex.Single

interface MatchRepository {
    fun saveImage(url: String, id: Int): Observable<Long>
    fun getImageList(): Observable<List<UserData>>
    fun saveFav(id: Pair<Long, Long>, isChecked: Boolean): Observable<Long>
    fun getFavData(topID: Long, bottomId: Long): Observable<Boolean>
    fun getCount(id: Pair<Long, Long>): Observable<Long>
    fun updateFavData(id: Pair<Long, Long>, isChecked: Boolean): Observable<Long>
}
