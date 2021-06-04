package com.example.petproject.ui.noteInfo

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.petproject.data.repository.NotesRepository
import com.example.petproject.di.AppModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteInfoViewModel @Inject constructor
    (private val notesRepository: NotesRepository) : ViewModel() {


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