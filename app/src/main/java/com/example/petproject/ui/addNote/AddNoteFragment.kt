package com.example.petproject.ui.addNote

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
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

    val addNoteViewModel by activityViewModels<AddNoteViewModel>()

    private fun onBackPressedAddNote(): OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("tag", "nen")
                addNoteViewModel.insert(
                    Note(
                        title = binding.title.text.toString(),
                        description = binding.description.text.toString()
                    )
                )
                view?.post { findNavController().popBackStack() }
            }
        }

    override fun init() {
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedAddNote())
    }
}