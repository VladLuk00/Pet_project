package com.example.petproject.ui.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petproject.data.model.Note
import com.example.petproject.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoticeViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    var listOfNotices: MutableLiveData<List<Note>> = MutableLiveData()

    fun insert(note: Note) = viewModelScope.launch {
            repository.insert(note)
    }

    fun setNotices() {
        CoroutineScope(Job()).launch(Dispatchers.Main) {
            val _listOfNoties = repository.listOfNotes()
            listOfNotices.value = _listOfNoties
        }
    }
}