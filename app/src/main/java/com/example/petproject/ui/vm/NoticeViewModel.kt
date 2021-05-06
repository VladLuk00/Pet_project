package com.example.petproject.ui.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.petproject.data.db.NotesDatabase
import com.example.petproject.data.pojo.Note
import com.example.petproject.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoticeViewModel @Inject constructor() : ViewModel() {

    var repository: Repository? = null
        @Inject set

    val listOfNotices: LiveData<List<Note>>? = repository?.listOfNotes().asLiveData()

    fun insert(note: Note) = viewModelScope.launch {
        Log.d("tag", "view model repository : $repository")
        repository?.insert(note)
    }
}