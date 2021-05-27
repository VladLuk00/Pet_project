package com.example.petproject.ui.notes.binding

import android.content.res.Resources
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.petproject.R
import com.example.petproject.ui.main.MainActivity
import com.example.petproject.ui.notes.adapters.RecycleViewItemDecoration
import com.example.petproject.ui.notes.binding.NoteRecycleViewBinding.onLongClickListener

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

    @BindingAdapter(value = ["onLongClick"])
    @JvmStatic
    fun RecyclerView.onLongClickListener(view: (View) -> Unit) {
        /*Log.d("tag", " before long click")
        Log.d("tag", "$this")
            setOnLongClickListener {
            Log.d("tag", "long click")
            val contextualMenuCallback = object : ActionMode.Callback {
                override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    mode?.menuInflater?.inflate(R.menu.menu_contextual_toolbar_list_note, menu)
                    return true
                }

                override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onDestroyActionMode(mode: ActionMode?) {
                    TODO("Not yet implemented")
                }
            }
            (context as MainActivity).startActionMode(contextualMenuCallback)
            true
        }*/
    }
}