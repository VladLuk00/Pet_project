package com.example.petproject.ui.directories.interactor

import com.example.petproject.data.db.LabelDao
import javax.inject.Inject

class LabelInteractor @Inject constructor(val labelDao: LabelDao) {

    fun getList() = labelDao.getListLabels()
}