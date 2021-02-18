package com.bmk.data

import android.util.Log
import com.bmk.data.db.InputData
import com.bmk.data.db.LocalDataBase
import com.bmk.domain.DataRequest
import com.bmk.domain.DataResponse
import com.bmk.domain.MatchRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    val db: LocalDataBase
) :
    MatchRepository {
    override fun getData(): Observable<List<DataResponse>> {

        return db.matchDOA().getAllData().map { data ->
            data.map {
                DataResponse(
                    id = Date().time.toString(),
                    fName = it.fName,
                    lName = it.lName,
                    mob = it.mob,
                    gender = it.gender,
                    dob = it.dob,
                    empNo = it.empNo,
                    empName = it.empName,
                    empdesg = it.empdesg,
                    accountType = it.accountType,
                    exp = it.exp,
                    bankName = it.bankName,
                    branch = it.branch,
                    acNo = it.acNo,
                    ifscCode = it.ifscCode,
                    image = it.image
                )
            }
        }/*.doOnNext{
            db.matchDOA().insertAll(
                InputData(
                    id = Date().time,
                    fName ="request.fName",
                    lName ="request.lName",
                    mob ="request.mob",
                    gender ="request.gender",
                    dob ="request.dob",
                    empNo ="request.empNo",
                    empName ="request.empName",
                    empdesg ="request.empdesg",
                    accountType ="request.accountType",
                    exp ="request.exp",
                    bankName ="request.bankName",
                    branch ="request.branch",
                    acNo ="request.acNo",
                    ifscCode ="request.ifscCode",
                    image ="request.image"
                )
            )
        }*/
    }

    override fun saveData(request: DataRequest): Single<Long> {
        return Single.fromCallable {
            db.matchDOA().insertAll(
                InputData(
                    id = Date().time,
                    fName = request.fName,
                    lName = request.lName,
                    mob = request.mob,
                    gender = request.gender,
                    dob = request.dob,
                    empNo = request.empNo,
                    empName = request.empName,
                    empdesg = request.empdesg,
                    accountType = request.accountType,
                    exp = request.exp,
                    bankName = request.bankName,
                    branch = request.branch,
                    acNo = request.acNo,
                    ifscCode = request.ifscCode,
                    image = request.image
                )
            )
        }
    }
}