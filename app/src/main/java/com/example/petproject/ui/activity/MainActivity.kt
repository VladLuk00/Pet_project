package com.example.petproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.petproject.R
import com.example.petproject.databinding.ActivityMainBinding
import com.example.petproject.ui.vm.NoticeViewModel

class MainActivity : AppCompatActivity() {

    val taskViewModel by viewModels<NoticeViewModel>()
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).run {
            lifecycleOwner = this@MainActivity
            viewModel = taskViewModel
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}