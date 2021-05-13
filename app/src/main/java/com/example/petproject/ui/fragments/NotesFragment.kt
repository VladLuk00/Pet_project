package com.example.petproject.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.petproject.R
import com.example.petproject.data.model.Note
import com.example.petproject.databinding.FragmentListNoteBinding
import com.example.petproject.ui.adapters.NewAdapter
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
        val notesAdapter = NewAdapter()
        val binding: FragmentListNoteBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_list_note, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.adapter = notesAdapter

        CoroutineScope(Job()).launch(Dispatchers.Default) {
            viewModel.setNotices()
            viewModel.insert(Note(description = "desc", title = "title"))
            viewModel.insert(Note(description = "desc", title = "title"))
            viewModel.insert(Note(description = "desc", title = "title"))
            viewModel.insert(Note(description = "desc", title = "title"))
        }

        viewModel.listOfNotices.observe(viewLifecycleOwner, Observer {
            it.let(notesAdapter::submitList)
        })

        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_list_note, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }
}