package com.example.petproject.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.petproject.R
import com.example.petproject.data.pojo.Note
import com.example.petproject.databinding.ActivityMainBinding
import com.example.petproject.ui.vm.NoticeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<NoticeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.listOfNotices.observe(this, Observer {
            Log.d("tag", "view model observe")
        })
        CoroutineScope(Job()).launch(Dispatchers.Default) {
            viewModel.insert(Note(title = "t", description = "d"))
            Log.d("tag", "${viewModel.listOfNotices.value?.size}")
        }
    }
}