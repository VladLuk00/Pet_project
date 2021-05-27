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

class MyHandler {
    fun onAddButtonClick(view: View) {
        val action = NotesFragmentDirections.actionNotesFragmentToAddNoteFragment()
        Navigation.findNavController(view).navigate(action)

    }

    fun onLongClickListener(view: View) {
        view.setOnLongClickListener {
            val contextualMenuCallback = object : ActionMode.Callback {
                override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    mode?.menuInflater?.inflate(R.menu.menu_contextual_toolbar_list_note, menu)
                    return true
                }

                override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onDestroyActionMode(mode: ActionMode?) {
                    TODO("Not yet implemented")
                }
            }
            (view.context as MainActivity).startActionMode(contextualMenuCallback)
            true
        }
    }

}