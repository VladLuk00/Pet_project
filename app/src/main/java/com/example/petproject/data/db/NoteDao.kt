package com.example.petproject.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.petproject.data.model.Note
import com.example.petproject.data.model.Notification


@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table")
    suspend fun getNotes(): List<Note>

    @Query("SELECT * FROM note_table WHERE noteId = :id")
    suspend fun getNoteById(id: Int): Note

    @Delete
    fun deleteNote(note: Note)

    @Insert
    fun insertNote(vararg note: Note)

    @Query("DELETE FROM note_table")
    fun delete()

    @Query("SELECT color FROM note_table")
    suspend fun getColor(): Int

    @Query("UPDATE note_table SET description = :description WHERE noteId = :id")
    suspend fun updateDescription(description: String, id: Int)

    @Query("UPDATE note_table SET color = :color WHERE noteId = :id")
    suspend fun updateColor(color: Int, id: Int)

    @Query("UPDATE note_table SET title = :title WHERE noteId = :id")
    suspend fun updateTitle(title: String, id: Int)

    @Query("UPDATE note_table SET year = :year, month = :month, day = :day WHERE noteId = :id")
    suspend fun updateDate(year: Int, month: Int, day: Int, id: Int)

    @Query("UPDATE note_table SET hour = :hour, minute = :minute WHERE noteId = :id")
    suspend fun updateTime(hour: Int, minute: Int, id: Int)
}