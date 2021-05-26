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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment(R.layout.fragment_add_note) {

    val addNoteViewModel by activityViewModels<AddNoteViewModel>()
    lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_add_note, container, false)
        binding.apply {
            lifecycleOwner = this@AddNoteFragment
        }

        val view = binding.root

        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedAddNote())
        return view
    }

    private fun onBackPressedAddNote(): OnBackPressedCallback = object : OnBackPressedCallback(true) {
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
}