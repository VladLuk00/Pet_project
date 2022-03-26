package com.example.petproject.ui.main

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.petproject.R
import com.example.petproject.ui.notes.OnLongClickListNotesItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    lateinit var navController: NavController
    lateinit var bottomNavigation: BottomNavigationView
    lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigation = findViewById(R.id.bottomNavigationView)
        bottomNavigation.setupWithNavController(navController)
        bottomNavigation.setOnNavigationItemSelectedListener {item ->
            onNavDestinationSelected(item, navController)
        }
        navController.addOnDestinationChangedListener(manageBottomNavigationListener())

        mainActivity = this
        Log.d("tag", "onCreate")
    }

    override fun onResume() {
        super.onResume()
    }

    private fun manageBottomNavigationListener(): NavController.OnDestinationChangedListener {

        fun showBottomNav() {
            bottomNavigation.visibility = View.VISIBLE
        }

        fun hideBottomNav() {
            bottomNavigation.visibility = View.GONE
        }

        return NavController.OnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.noteInfoFragment -> hideBottomNav()
                R.id.addNoteFragment -> hideBottomNav()
                R.id.directoriesNotesFragment -> hideBottomNav()/*showBottomNav()*/
                R.id.notesFragment -> hideBottomNav()/*showBottomNav()*/
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        //navController.popBackStack()
    }
}