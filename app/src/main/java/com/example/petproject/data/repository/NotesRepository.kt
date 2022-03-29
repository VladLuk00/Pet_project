package com.example.petproject.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.petproject.data.db.NoteDao
import com.example.petproject.data.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

    suspend fun getColor(): Int {
        return noteDao.getColor()
    }
    suspend fun updateDescription(desciption: String, id: Int) {
        noteDao.updateDescription(desciption, id)
    }

    suspend fun updateColor(color: Int, id: Int) {
        noteDao.updateColor(color, id)
    }

    suspend fun updateTitle(title: String, id: Int) {
        noteDao.updateTitle(title, id)
    }

    suspend fun updateDate(year: Int, month: Int, day: Int, id: Int) {
            noteDao.updateDate(year, month, day, id)
    }

    suspend fun updateTime(hour: Int, minute: Int, id: Int) {
        noteDao.updateTime(hour, minute, id)
    }

    suspend fun getNote(id: Int) = noteDao.getNoteById(id)
}