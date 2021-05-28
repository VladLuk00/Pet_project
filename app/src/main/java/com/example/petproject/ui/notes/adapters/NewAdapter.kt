package com.example.petproject.ui.notes.adapters

import android.app.Activity
import android.util.Log
import android.view.*
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.petproject.R
import com.example.petproject.data.model.Note
import com.example.petproject.databinding.RecycleViewItemNoteBinding
import com.example.petproject.ui.main.MainActivity
import com.example.petproject.ui.notes.NotesFragment
import com.example.petproject.ui.notes.OnLongClickListNotesItem

class NewAdapter(
    val onLongClickListNotesItem: OnLongClickListNotesItem,
    val onItemClick: (Note) -> Unit
) :
    ListAdapter<Note, NewAdapter.NotesViewHolder>(Companion) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecycleViewItemNoteBinding.inflate(layoutInflater)

        return NotesViewHolder(binding)

    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentUser = getItem(position)
        holder.binding.note = currentUser
        holder.binding.executePendingBindings()
        holder.itemView.setOnClickListener { onItemClick(currentUser) }
        holder.itemView.setOnLongClickListener { onLongClickListNotesItem.onLongClick(currentUser); true }
    }

    class NotesViewHolder(val binding: RecycleViewItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean = oldItem === newItem

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean = oldItem == newItem
        override fun getChangePayload(oldItem: Note, newItem: Note): Any? {
            Log.d("tag", "getChanged")
            return null
        }
    }
}