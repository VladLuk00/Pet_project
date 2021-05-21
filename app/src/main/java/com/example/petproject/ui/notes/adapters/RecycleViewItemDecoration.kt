package com.example.petproject.ui.notes.adapters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecycleViewItemDecoration(private val spaceAround: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        //super.getItemOffsets(outRect, view, parent, state)
        outRect.left = spaceAround
        outRect.right = spaceAround
        outRect.bottom = spaceAround
        outRect.top = spaceAround
    }
}