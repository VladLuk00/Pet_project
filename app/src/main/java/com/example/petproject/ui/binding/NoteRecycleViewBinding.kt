package com.example.petproject.ui.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.petproject.data.model.Note
import com.example.petproject.ui.adapters.NewAdapter

object NoteRecycleViewBinding {

    @BindingAdapter(value = ["setCustomAdapter"])
    @JvmStatic
    fun RecyclerView.bindRecycleViewAdapter(adapter: RecyclerView.Adapter<*>) {
        this.adapter = adapter
        this.setHasFixedSize(true)
    }

}