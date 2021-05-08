package com.example.petproject.ui.fragments

import android.R.attr.data
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.example.petproject.R
import com.example.petproject.data.pojo.Note
import com.example.petproject.databinding.FragmentListNoteBinding
import com.example.petproject.ui.vm.NoticeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class NotesFragment : Fragment(R.layout.fragment_list_note) {

    private val viewModel by activityViewModels<NoticeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentListNoteBinding =
            DataBindingUtil.inflate(layoutInflater,R.layout.fragment_list_note, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.listOfNotices.observe(viewLifecycleOwner, Observer {
            Log.d("tag", "view model observe")
        })
        CoroutineScope(Job()).launch(Dispatchers.Default) {
            viewModel.insert(Note(title = "t", description = "d"))
            Log.d("tag", "${viewModel.listOfNotices.value?.size}")
        }

        val view = binding.root

        return view
    }
}