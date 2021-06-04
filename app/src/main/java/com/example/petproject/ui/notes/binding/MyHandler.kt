package com.example.petproject.ui.notes.binding

import android.content.Intent
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.Navigation
import com.example.petproject.R
import com.example.petproject.ui.addNote.AddNoteActivity
import com.example.petproject.ui.main.MainActivity
import com.example.petproject.ui.notes.NotesFragmentDirections
import javax.inject.Inject

class MyHandler @Inject constructor() {
    fun onAddButtonClick(view: View) {
        val action = NotesFragmentDirections.actionNotesFragmentToAddNoteFragment()
        Navigation.findNavController(view).navigate(action)

    }
}