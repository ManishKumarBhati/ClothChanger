package com.bmk.daggerproject.data

import com.bmk.daggerproject.api.ApiServiceInterface
import com.bmk.daggerproject.data.db.MatchData
import com.bmk.daggerproject.data.db.MatchDataBase
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
        return api.getPostList("shapes").map { res ->
            res.data.filter { it.images != null }.mapNotNull {
                val img = it.images!!.firstOrNull()
                ResponseData(
                    id = it.id,
                    title = it.title,
                    imgUrl = if (img != null && !img.animated) img.link else null

                )
            }
        }
    }

    override fun getTeam(): Single<String> {
        return getMatchLocalData()
    }

    private fun addMatchData(data: String) {
        db.matchDOA().insertAll(MatchData(data))
    }

    private fun getMatchLocalData(): Single<String> {
        return db.matchDOA().getAlData()
    }
}