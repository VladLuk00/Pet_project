package com.example.petproject.data.db

import androidx.room.RoomDatabase
import com.example.petproject.data.model.Note

@androidx.room.Database(entities = [Note::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

}