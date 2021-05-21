package com.example.petproject.ui.directories.interactor

import com.example.petproject.data.db.LabelDao
import com.example.petproject.data.model.Label
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LabelInteractor @Inject constructor(val labelDao: LabelDao) {

    fun getList(): Single<List<Label>> = labelDao.getListLabels()

    suspend fun insertLabel(label: Label) {
        labelDao.insertLabel(label)
    }
}