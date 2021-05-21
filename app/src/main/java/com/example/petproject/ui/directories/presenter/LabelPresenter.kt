package com.example.petproject.ui.directories.presenter

import android.util.Log
import com.example.petproject.data.model.Label
import com.example.petproject.ui.directories.interactor.LabelInteractor
import com.example.petproject.ui.directories.view.adapters.RecycleViewLabelAdapter

import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LabelPresenter @Inject constructor(
    val recycleViewLabelAdapter: RecycleViewLabelAdapter,
    val labelInteractor: LabelInteractor
) {

    fun updateLabelList() {
        labelInteractor.getList()
            .subscribeOn(Schedulers.newThread())
            .subscribe { it ->
                Log.d("tag", "List: $it")
                recycleViewLabelAdapter.set(it)
            }
    }

    fun insertLabel(label: Label) {
        CoroutineScope(Dispatchers.Default).launch {
            labelInteractor.insertLabel(label)
        }
    }
}