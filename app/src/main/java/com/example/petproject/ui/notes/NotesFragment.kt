package com.example.petproject.ui.notes

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.petproject.R
import com.example.petproject.databinding.FragmentListNoteBinding
import com.example.petproject.ui.notes.adapters.NewAdapter
import com.example.petproject.ui.notes.binding.MyHandler
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.view.ActionMode
import androidx.navigation.fragment.findNavController
import com.example.petproject.data.model.Note
import com.example.petproject.ui.base.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import javax.inject.Inject


@AndroidEntryPoint
class NotesFragment(override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListNoteBinding = FragmentListNoteBinding::inflate) :
    BaseFragment<FragmentListNoteBinding>(), OnLongClickListNotesItem {

    val noticeViewModel by hiltNavGraphViewModels<NotesViewModel>(R.id.nav_graph)

    @Inject
    lateinit var myHandler: MyHandler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noticeViewModel.setNotices()

    }

    override fun init() {
        val notesAdapter = NewAdapter(this) {
            val bundle: Bundle = Bundle().apply {
                putString("description", it.description)
                putString("title", it.title)
                putInt("id", it.noteId)
            }
            //findNavController().setGraph(R.navigation.graph_note_info, bundle)
            findNavController().navigate(R.id.graph_note_info, bundle)
        }

        binding.apply {
            viewModel = noticeViewModel
            adapter = notesAdapter
            handler = myHandler
        }

        noticeViewModel.listOfNotices.observe(viewLifecycleOwner, Observer {
            it.let(notesAdapter::submitList)
        })

        val toolbar = binding.toolbarListNoteId as Toolbar
        toolbar.inflateMenu(R.menu.menu_toolbar_note_info)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_list_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setBackgroundImage() {
        val imageContainer: ImageView? = binding.root.findViewById(R.id.pictureContainer)
        val url = "https://picsum.photos/${imageContainer?.width}/${imageContainer?.height}/?random"
        Glide.with(this)
            .load(url)
            .into(imageContainer!!)
    }


    override fun onLongClick(note: Note) {
        val callbackList = object : ActionMode.Callback {
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                mode?.menuInflater?.inflate(R.menu.menu_contextual_toolbar_list_note, menu)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return true
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return when (item?.itemId) {
                    R.id.list_contextual_delete -> {
                        CoroutineScope(Dispatchers.Default).launch {
                            noticeViewModel.deleteNote(note)
                        }
                        noticeViewModel.setNotices()
                        mode?.finish()
                        true
                    }
                    else -> false
                }
            }

            override fun onDestroyActionMode(mode: ActionMode?) {

            }
        }
        (activity as AppCompatActivity).startSupportActionMode(callbackList)
        //toolbar.startActionMode(callbackList)
    }
}