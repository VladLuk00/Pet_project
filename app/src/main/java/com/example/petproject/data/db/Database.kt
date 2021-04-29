package com.example.petproject.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.petproject.data.pojo.Note

@androidx.room.Database(entities = [Note::class], version = 1)
abstract class Database() : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    var INSTANCE: Database? = null

    fun getDatabase(context: Context): Database {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context
                , Database::class.java, "note_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}