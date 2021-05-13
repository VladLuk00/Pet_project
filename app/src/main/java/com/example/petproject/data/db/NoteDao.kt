package com.example.petproject.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.petproject.data.model.Note


@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table")
    suspend fun getNotes(): List<Note>

    @Delete
   fun deleteNote(note: Note)

    @Insert
    fun insertNote(vararg note: Note)

    @Delete
    fun delete()
}