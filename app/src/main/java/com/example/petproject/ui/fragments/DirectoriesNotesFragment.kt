package com.example.petproject.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.petproject.R

class DirectoriesNotesFragment : Fragment(R.layout.fragment_list_directory) {

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_list_note, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }
}