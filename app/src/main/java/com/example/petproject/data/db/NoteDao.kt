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

    @Query("DELETE FROM note_table")
    fun delete()

    @Query("UPDATE note_table SET description = :description WHERE noteId = :id")
    suspend fun updateDescription(description: String, id: Int)

    @Query("UPDATE note_table SET title = :title WHERE noteId = :id")
    suspend fun updateTitle(title: String, id: Int)
}