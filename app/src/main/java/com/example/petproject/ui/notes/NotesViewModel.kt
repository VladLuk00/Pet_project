package com.example.petproject.ui.notes

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petproject.data.model.Note
import com.example.petproject.data.repository.NotesRepository
import com.example.petproject.ui.addNote.AddNoteActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NotesViewModel @Inject constructor(private val notesRepository: NotesRepository) : ViewModel() {

    var listOfNotices: MutableLiveData<List<Note>> = MutableLiveData()

    fun insert(note: Note) = viewModelScope.launch {
            notesRepository.insert(note)
    }

    fun setNotices() {
        CoroutineScope(Dispatchers.Main).launch {
            val _listOfNoties = notesRepository.listOfNotes().reversed()
            listOfNotices.value = _listOfNoties
        }
    }

    fun delete() {
        CoroutineScope(Job()).launch(Dispatchers.Default) {
            notesRepository.delete()
        }
    }
}