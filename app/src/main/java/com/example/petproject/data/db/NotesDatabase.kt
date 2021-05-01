package com.example.petproject.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.petproject.data.pojo.Note

@androidx.room.Database(entities = [Note::class], version = 2)
abstract class NotesDatabase() : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context
                    , NotesDatabase::class.java, "note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}