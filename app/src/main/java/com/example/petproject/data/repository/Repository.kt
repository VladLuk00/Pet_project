package com.example.petproject.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.petproject.data.db.NoteDao
import com.example.petproject.data.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class Repository @Inject constructor(val noteDao: NoteDao) {

    fun insert(note: Note) {
        CoroutineScope(Job()).launch {
            noteDao.insertNote(note)
            Log.d("tag", "get note ${noteDao.getNotes()}")
        }
    }

    suspend fun listOfNotes(): List<Note> {
        return noteDao.getNotes()
    }
}