package com.example.petproject.ui.directories.presenter

import android.util.Log
import androidx.compose.ui.node.ViewAdapter
import com.example.petproject.data.model.Label
import com.example.petproject.ui.directories.interactor.LabelInteractor
import com.example.petproject.ui.directories.view.RecycleViewLabelAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable

import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class LabelPresenter @Inject constructor(
    val recycleViewLabelAdapter: RecycleViewLabelAdapter,
    val labelInteractor: LabelInteractor
) {

    fun updateLabelList() {
        labelInteractor.getList()
            .subscribeOn(Schedulers.newThread())
            .subscribe { it ->
                Log.d("tag", "$it")
                recycleViewLabelAdapter.set(it)
            }
    }

    fun insertLabel(label: Label): Observable<Boolean> {
        labelInteractor.insertLabel(label)
        return Observable.just(true)
    }
}