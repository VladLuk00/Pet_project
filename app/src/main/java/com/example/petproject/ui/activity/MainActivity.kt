package com.example.petproject.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.petproject.R
import com.example.petproject.data.db.NotesDatabase
import com.example.petproject.data.pojo.Note
import com.example.petproject.data.repository.Repository
import com.example.petproject.databinding.FragmentBlankBinding
import com.example.petproject.ui.adapters.NoteRecycleViewAdapter
import com.example.petproject.ui.vm.NoticeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavMenu = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavMenu.setupWithNavController(navController)
    }

    fun bottomNavigationListener() {
        val navigation = BottomNavigationView.OnNavigationItemSelectedListener {item ->
            when(item.itemId) {

            }
        }
    }
}