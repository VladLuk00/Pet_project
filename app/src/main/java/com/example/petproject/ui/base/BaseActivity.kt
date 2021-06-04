package com.example.petproject.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<ViewBind : ViewBinding> : AppCompatActivity() {

    var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> ViewBind

    val binding: ViewBind = _binding as ViewBind

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
    }
}