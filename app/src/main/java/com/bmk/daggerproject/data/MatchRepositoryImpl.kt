package com.bmk.daggerproject.data

import com.bmk.daggerproject.api.ApiServiceInterface
import com.bmk.daggerproject.data.db.MatchData
import com.bmk.daggerproject.data.db.MatchDataBase
import com.bmk.daggerproject.domain.DetailsData
import com.bmk.daggerproject.domain.MatchRepository
import com.bmk.daggerproject.domain.ResponseData
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    val api: ApiServiceInterface,
    val db: MatchDataBase
) :
    MatchRepository {
    override fun getMatchData(): Observable<List<ResponseData>> {
        return getMatchData("shape")
    }

    override fun getMatchData(query: String): Observable<List<ResponseData>> {
        return api.getPostList(query).map { res ->
            res.data.filter { it.images != null }.map {
                val img = it.images!!.firstOrNull()
                ResponseData(
                    id = it.id,
                    title = it.title,
                    imgUrl = if (img != null && !img.animated) img.link else null

                )
            }
                .filter { it.imgUrl != null }
        }.doOnNext(this::addMatchData)
    }

    private fun addMatchData(response: List<ResponseData>) {
        val data = response.map {
            MatchData(id = it.id, title = it.title, imgUrl = it.imgUrl!!)
        }
        db.matchDOA().insertAll(data)
    }

    override fun getData(id: String): Observable<DetailsData> {
        return db.matchDOA().getData(id).map {
            DetailsData(
                id = it.id,
                title = it.title,
                imgUrl = it.imgUrl,
                comment = it.comment
            )
        }
    }

    override fun saveComment(id: String, comment: String): Single<Int> {
        return db.matchDOA().addComment(id, comment)
    }
}