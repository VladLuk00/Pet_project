package com.example.petproject.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.petproject.data.repository.Repository
import com.example.petproject.databinding.RecycleViewItemNoteBinding
import com.example.petproject.ui.vm.NoticeViewModel

class NoteRecycleViewAdapter (val viewModel: NoticeViewModel) : RecyclerView.Adapter<NoteRecycleViewAdapter.NoteViewHolder>() {

    val listOfNotes = viewModel.listOfNotices.value

    class NoteViewHolder(val binding: RecycleViewItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecycleViewItemNoteBinding.inflate(layoutInflater)
        Log.d("tag"," adapter $listOfNotes")

        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfNotes?.size ?: 0
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        Log.d("tag"," bind $listOfNotes")
        val note = listOfNotes?.get(position)
        holder.binding.note = note
        holder.binding.executePendingBindings()
    }
}
