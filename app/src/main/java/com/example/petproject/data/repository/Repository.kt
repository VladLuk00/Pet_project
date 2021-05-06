package com.example.petproject.data.repository

import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.room.Room
import com.example.petproject.data.db.NotesDatabase
import com.example.petproject.data.db.NoteDao
import com.example.petproject.data.pojo.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class Repository @Inject constructor(val noteDao: NoteDao) {

    fun insert(note: Note) {
        CoroutineScope(Job()).launch {
            noteDao.insertNote(note)
            Log.d("tag", "get note ${noteDao.getNotes().asLiveData().value}")
        }
    }

    fun listOfNotes(): Flow<List<Note>> {
        Log.d("tag", " listOfNotes() ${noteDao.getNotes().asLiveData().value}")
        return noteDao.getNotes()
    }
}