package com.example.petproject.ui.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petproject.data.model.Note
import com.example.petproject.data.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoticeViewModel @Inject constructor(val notesRepository: NotesRepository) : ViewModel() {

    var listOfNotices: MutableLiveData<List<Note>> = MutableLiveData()

    fun insert(note: Note) = viewModelScope.launch {
            notesRepository.insert(note)
    }

    fun setNotices() {
        CoroutineScope(Dispatchers.Main).launch {
            val _listOfNoties = notesRepository.listOfNotes()
            listOfNotices.value = _listOfNoties
        }
    }

    fun delete() {
        CoroutineScope(Job()).launch(Dispatchers.Default) {
            notesRepository.delete()
        }
    }

    fun updateDescription(description: String, id: Int) {
        CoroutineScope(Dispatchers.Default).launch {
            notesRepository.updateDescription(description, id)
        }

    }

    fun updateTitle(title: String, id: Int) {
        CoroutineScope(Dispatchers.Default).launch {
            notesRepository.updateTitle(title, id)
        }
    }
}