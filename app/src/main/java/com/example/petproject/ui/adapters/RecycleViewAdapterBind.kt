package com.example.petproject.ui.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

class RecycleViewAdapterBind {
    @BindingAdapter(value = ["setAdapter"])
    fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
        this.run {
            this.adapter = adapter
        }
    }
}