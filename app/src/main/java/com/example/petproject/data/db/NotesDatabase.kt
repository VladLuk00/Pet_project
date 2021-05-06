package com.example.petproject.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.petproject.data.pojo.Note

@androidx.room.Database(entities = [Note::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

}