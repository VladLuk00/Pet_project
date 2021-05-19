package com.example.petproject.ui.directories.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.petproject.R
import petrov.kristiyan.colorpicker.ColorPicker

class DirectoriesNotesFragment : Fragment(R.layout.fragment_list_directory) {

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_list_note, menu)

        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val colorPicker = ColorPicker(activity)
        colorPicker.show()
    }
}