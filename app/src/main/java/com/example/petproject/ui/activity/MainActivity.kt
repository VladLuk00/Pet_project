package com.example.petproject.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.petproject.R
import com.example.petproject.data.db.Database
import com.example.petproject.data.pojo.Note
import com.example.petproject.data.repository.Repository
import com.example.petproject.databinding.ActivityMainBinding
import com.example.petproject.ui.adapters.NoteRecycleViewAdapter
import com.example.petproject.ui.vm.NoticeViewModel


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this@MainActivity).get(NoticeViewModel::class.java)

        val activityBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityBinding.lifecycleOwner = this@MainActivity
        activityBinding.viewModel = viewModel

        val noteAdapter = NoteRecycleViewAdapter(viewModel)
        activityBinding.adapter = noteAdapter

        viewModel.insert(Note(title = "title", description = "description"))

        viewModel.listOfNotices.observe(this, Observer {

        })
    }
}