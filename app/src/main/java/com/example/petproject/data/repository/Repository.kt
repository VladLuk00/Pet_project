package com.example.petproject.data.repository

import androidx.room.Room
import com.example.petproject.data.db.Database
import com.example.petproject.data.db.NoteDao
import com.example.petproject.data.pojo.Note
import kotlinx.coroutines.flow.Flow

class Repository(val noteDao: NoteDao) {

    fun insert(note: Note) {
        noteDao.insertNote(note)
    }

    fun listOfNotes(): Flow<List<Note>> = noteDao.getNotes()
}