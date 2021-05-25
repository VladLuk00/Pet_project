package com.example.petproject.ui.notes.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petproject.ui.notes.adapters.RecycleViewItemDecoration

object NoteRecycleViewBinding {

    @BindingAdapter(value = ["setCustomAdapter"])
    @JvmStatic
    fun RecyclerView.bindRecycleViewAdapter(adapter: RecyclerView.Adapter<*>) {
        this.adapter = adapter
        this.setHasFixedSize(true)
    }

    @BindingAdapter(value = ["setSpaceItemDecoration"])
    @JvmStatic
    fun RecyclerView.setItemDecoration(space: Float) {
        val decoration = RecycleViewItemDecoration(space.toInt())
        this.addItemDecoration(decoration)
    }

    @BindingAdapter(value = ["app:newUrl"])
    @JvmStatic
    fun setNewUrl(view: ImageView) {
        Glide.with(view).load("").into(view)
    }

}