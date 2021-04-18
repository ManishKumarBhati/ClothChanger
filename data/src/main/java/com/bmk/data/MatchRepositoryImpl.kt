package com.bmk.data

import com.bmk.data.db.InputData
import com.bmk.data.db.LocalDataBase
import com.bmk.data.db.UserClothData
import com.bmk.domain.*
import com.bmk.domain.DataResponse
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(val db: LocalDataBase) : MatchRepository {
    override fun getData(): Observable<List<DataResponse>> {
        return db.matchDOA().getAllData().map { data ->
            data.map {
                DataResponse(
                    id = it.id,
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
        }
    }

    override fun saveData(request: DataRequest): Observable<Long> {
        return Observable.just(1L)
            .doOnNext {
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
                    topbottom = it.topbottom,
                    isFav = it.isFav
                )
            }
        }
    }

    override fun getPersonalData(id: Long): Observable<PersonalData> {
        return db.matchDOA().getData(id).map {
            PersonalData(
                fName = it.fName,
                lName = it.lName,
                mob = it.mob,
                gender = it.gender,
                dob = it.dob
            )
        }
    }

    override fun getEmpData(id: Long): Observable<EmployeeData> {
        return db.matchDOA().getData(id).map {
            EmployeeData(
                empNo = it.empNo,
                empName = it.empName,
                empdesg = it.empdesg,
                accountType = it.accountType,
                exp = it.exp
            )
        }
    }

    override fun getBankData(id: Long): Observable<BankData> {
        return db.matchDOA().getData(id).map {
            BankData(
                bankName = it.bankName,
                branch = it.branch,
                acNo = it.acNo,
                ifscCode = it.ifscCode,
                image = it.image
            )
        }
    }

    override fun getModifyData(id: Long, request: DataRequest): Observable<Int> {
        return db.matchDOA().updateData(
            id = id,
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
        ).toObservable()
    }
}