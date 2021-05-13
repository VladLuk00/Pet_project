package com.example.petproject.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.petproject.data.model.Note
import com.example.petproject.databinding.RecycleViewItemNoteBinding

class NewAdapter : ListAdapter<Note, NewAdapter.NotesViewHolder> (Companion) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecycleViewItemNoteBinding.inflate(layoutInflater)

        return NotesViewHolder(binding)
    }


    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        Log.d("tag", "bind")
        val currentUser = getItem(position)
        holder.binding.note = currentUser
        holder.binding.executePendingBindings()

    }

    class NotesViewHolder(val binding: RecycleViewItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root)



    companion object: DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return true
        }
    }
}