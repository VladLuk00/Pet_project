package com.example.petproject.ui.notes


import android.view.View
import androidx.appcompat.view.ActionMode
import com.example.petproject.data.model.Note

interface OnLongClickListNotesItem {
    fun onLongClick(note: Note)
}