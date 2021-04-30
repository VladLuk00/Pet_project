package com.example.petproject.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.petproject.data.pojo.Note
import com.example.petproject.data.repository.Repository
import kotlinx.coroutines.launch

class NoticeViewModel(val repository: Repository) : ViewModel() {

    val listOfNotices: LiveData<List<Note>> = repository.listOfNotes().asLiveData()

    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }


}