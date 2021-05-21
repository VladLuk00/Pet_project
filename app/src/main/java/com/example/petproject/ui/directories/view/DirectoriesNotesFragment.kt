package com.example.petproject.ui.directories.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petproject.R
import com.example.petproject.ui.directories.presenter.LabelPresenter
import com.example.petproject.ui.directories.view.adapters.RecycleViewLabelAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DirectoriesNotesFragment : Fragment(R.layout.fragment_list_directory) {

    lateinit var recycleView: RecyclerView
    @Inject lateinit var recycleViewLabelAdapter: RecycleViewLabelAdapter
    @Inject lateinit var presenter: LabelPresenter

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_list_note, menu)

        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val colorPicker = ColorPicker(activity)
        colorPicker.show()*/
        recycleView = view.findViewById(R.id.recycleViewLabel)
        recycleView.adapter = recycleViewLabelAdapter
        recycleView.layoutManager = LinearLayoutManager(context)
    }

    override fun onStart() {
        super.onStart()

        /*CoroutineScope(Dispatchers.Main).launch {
            presenter.insertLabel(Label(name = "name"))
        }*/
        presenter.updateLabelList()
    }
}