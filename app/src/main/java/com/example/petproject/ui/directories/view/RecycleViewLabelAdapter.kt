package com.example.petproject.ui.directories.view

import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petproject.R
import com.example.petproject.data.model.Label

class RecycleViewLabelAdapter(private var taskList: List<Label>) :
        RecyclerView.Adapter<RecycleViewLabelAdapter.ViewHolder>() {

        inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            val title: TextView = view.findViewById(R.id.textViewLabelText)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycle_view_label_item, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return taskList.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.title.text = SpannableStringBuilder(taskList[position].name)

        }

        fun set(list: List<Label>) {
            taskList = list
            notifyDataSetChanged()
        }
}