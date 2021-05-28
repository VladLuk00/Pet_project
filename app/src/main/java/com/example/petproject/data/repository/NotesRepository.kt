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

class NotesRepository @Inject constructor(val noteDao: NoteDao) {

    fun insert(note: Note) {
        CoroutineScope(Job()).launch {
            noteDao.insertNote(note)
            Log.d("tag", "get note ${noteDao.getNotes()}")
        }
    }

    suspend fun listOfNotes(): List<Note> {
        return noteDao.getNotes()
    }

    fun delete() {
        noteDao.delete()
    }

    fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    suspend fun updateDescription(desciption: String, id: Int) {
        noteDao.updateDescription(desciption, id)
    }

    suspend fun updateTitle(title: String, id: Int) {
        noteDao.updateTitle(title, id)
    }
}