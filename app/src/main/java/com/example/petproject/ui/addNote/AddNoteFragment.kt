package com.example.petproject.ui.addNote

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.petproject.R
import com.example.petproject.data.model.Note
import com.example.petproject.databinding.FragmentAddNoteBinding
import com.example.petproject.ui.addNote.binding.AddNoteViewBinding
import com.example.petproject.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment(
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddNoteBinding = FragmentAddNoteBinding::inflate
) : BaseFragment<FragmentAddNoteBinding>() {

    private val addNoteViewModel by activityViewModels<AddNoteViewModel>()

    override fun init() {
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressed {
            binding.run {
                if (noteInfo.description.text.isNotEmpty()) {
                    addNoteViewModel.insert(
                        Note(
                            title = noteInfo.title.text.toString(),
                            description = noteInfo.description.text.toString()
                        )
                    )
                }
            }
            view?.post { findNavController().popBackStack() }
        })

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar as Toolbar)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true);
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true);
    }


}