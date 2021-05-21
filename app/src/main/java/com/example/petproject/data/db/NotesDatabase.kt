package com.example.petproject.data.db

import androidx.room.RoomDatabase
import com.example.petproject.data.model.Label
import com.example.petproject.data.model.Note

@androidx.room.Database(entities = [Note::class, Label::class], version = 5)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun labelDao(): LabelDao
}