package com.example.petproject.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.petproject.data.pojo.Note
import kotlinx.coroutines.flow.Flow
import java.util.*


@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table")
    fun getNotes(): Flow<List<Note>>

   @Delete
   fun deleteNote(note: Note)

    @Insert
    fun insertNote(note: Note)
}