package com.example.petproject.ui.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object NoteRecycleViewBinding {

    @BindingAdapter(value = ["setCustomAdapter"])
    @JvmStatic
    fun RecyclerView.bindRecycleViewAdapter(adapter: RecyclerView.Adapter<*>) {
        this.adapter = adapter
        this.setHasFixedSize(true)
    }
}