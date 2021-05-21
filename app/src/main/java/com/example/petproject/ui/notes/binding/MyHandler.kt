package com.example.petproject.ui.notes.binding

import android.content.Intent
import android.util.Log
import android.view.View
import com.example.petproject.ui.addNote.AddNoteActivity

class MyHandler {
    fun onAddButtonClick(view: View) {
        Log.d("tag", "onAddButtonClick")
        val intent = Intent(view.context, AddNoteActivity::class.java)
        view.context.startActivity(intent)
    }
}