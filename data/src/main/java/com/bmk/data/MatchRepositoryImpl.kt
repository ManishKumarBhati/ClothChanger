package com.bmk.data

import com.bmk.data.db.FavData
import com.bmk.data.db.LocalDataBase
import com.bmk.data.db.UserClothData
import com.bmk.domain.*
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(val db: LocalDataBase) : MatchRepository {
    override fun saveImage(url: String, id: Int): Observable<Long> {
        return Observable.fromCallable {
            db.matchDOA().insert(
                UserClothData(
                    id = Date().time,
                    image = url,
                    topbottom = id
                )
            )
        }
    }

    override fun getImageList(): Observable<List<UserData>> {
        return db.matchDOA().getUserData().map { res ->
            res.map {
                UserData(
                    id = it.id,
                    image = it.image,
                    topbottom = it.topbottom
                )
            }
        }
    }

    override fun saveFav(id: Pair<Long, Long>, isChecked: Boolean): Observable<Long> {
        val request = FavData(
            id = Date().time,
            topId = id.first,
            bottomId = id.second,
            isFav = isChecked
        )
        return Observable.just(db.matchDOA().insert(request))
            .map { it }

    }


    override fun getFavData(topID: Long, bottomId: Long): Observable<Boolean> {
        return db.matchDOA().getFavData(topID, bottomId)
            .map { it.isFav }
            .onErrorReturnItem(false)
            .toObservable()
    }

    override fun getCount(id: Pair<Long, Long>): Observable<Long> {
        return db.matchDOA().getFavCount(topId = id.first, bottomId = id.second)
            .map { it.id }
            .onErrorReturnItem(-1L)
            .toObservable()
    }

    override fun updateFavData(id: Pair<Long, Long>, isChecked: Boolean): Observable<Long> {
        return db.matchDOA()
            .updateData(topId = id.first, bottomId = id.second, isFav = isChecked)
            .map { it.toLong() }
            .toObservable()
    }
}