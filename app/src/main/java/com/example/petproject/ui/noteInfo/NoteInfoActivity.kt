package com.example.petproject.ui.noteInfo

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.petproject.R
import com.example.petproject.ui.noteInfo.dialogs.NotificationDialog
import dagger.hilt.android.AndroidEntryPoint
import petrov.kristiyan.colorpicker.ColorPicker

@AndroidEntryPoint
class NoteInfoActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_info)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragmentNoteInfo) as NavHostFragment
        navController = navHostFragment.navController
        val configuration = AppBarConfiguration(navController.graph)
        val toolbar: Toolbar = findViewById(R.id.toolbarNoteInfo)

        NavigationUI.setupWithNavController(toolbar, navController, configuration)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_note_info, menu)
        Log.d("tag", "menu create")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.notification -> {
                val notificationDialog = NotificationDialog()
                notificationDialog.show(supportFragmentManager, "notificationDialog")
                true
            }
            R.id.color -> {
                val colorPicker = ColorPicker(NoteInfoActivity())
                colorPicker.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}