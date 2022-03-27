package com.example.petproject.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.petproject.data.model.Note

abstract class BaseFragment<ViewBind : ViewBinding>() : Fragment() {

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ViewBind
    protected val binding
        get() = _binding as ViewBind

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        init()
        return requireNotNull(_binding).root
    }

    protected fun onBackPressed(func: () -> Unit): OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                func.invoke()
            }
        }

    abstract fun init()
}