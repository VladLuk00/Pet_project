package com.example.petproject.ui.directories.interactor

import com.example.petproject.data.db.LabelDao
import com.example.petproject.data.model.Label
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LabelInteractor @Inject constructor(val labelDao: LabelDao) {

    fun getList(): Single<List<Label>> = labelDao.getListLabels()

    fun insertLabel(label: Label) : Observable<Boolean> {
        labelDao.insertLabel(label)
        return Observable.just(true)
    }
}