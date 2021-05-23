package com.example.petproject.ui.notes.binding

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import com.example.petproject.ui.addNote.AddNoteActivity
import com.example.petproject.ui.notes.NotesFragmentDirections

class MyHandler {
    fun onAddButtonClick(view: View) {
        val action = NotesFragmentDirections.actionNotesFragmentToAddNoteFragment()
        Navigation.findNavController(view).navigate(action)

    }
}