package com.example.petproject.ui.notes.binding

import android.content.res.Resources
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
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

    @BindingAdapter(value = ["newUrl"])
    @JvmStatic
    fun ImageView.setNewUrl(some: String) {
        val window = Resources.getSystem().getDisplayMetrics()
        val url = "https://picsum.photos/${window.widthPixels}/${window.heightPixels}/?random"
        //val url = "https://i.picsum.photos/id/642/1080/2131.jpg?hmac=DQ7IJEeznMxwqHnip22KYVto2dQ1SrFszmi1oJT69C8"
        Log.d("tag", url)
        Glide.with(this)
            .load(url)
            //.diskCacheStrategy(DiskCacheStrategy.NONE)
            .centerCrop()
            .into(this)
    }

}