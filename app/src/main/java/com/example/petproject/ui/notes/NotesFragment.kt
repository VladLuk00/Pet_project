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


@AndroidEntryPoint
class NotesFragment : Fragment(R.layout.fragment_list_note), OnLongClickListNotesItem {

    val noticeViewModel by hiltNavGraphViewModels<NotesViewModel>(R.id.nav_graph)
    lateinit var binding: FragmentListNoteBinding
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val notesAdapter = NewAdapter(this) {
            val bundle: Bundle = Bundle()
            bundle.putString("description", it.description)
            bundle.putString("title", it.title)
            bundle.putInt("id", it.noteId)
            //findNavController().setGraph(R.navigation.graph_note_info, bundle)
            view?.findNavController()?.navigate(R.id.graph_note_info, bundle)
        }

        val myhandler = MyHandler()

        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_list_note, container, false)
        binding.apply {
            viewModel = noticeViewModel
            lifecycleOwner = this@NotesFragment
            adapter = notesAdapter
            handler = myhandler

        }

        noticeViewModel.listOfNotices.observe(viewLifecycleOwner, Observer {
            it.let(notesAdapter::submitList)
        })

        noticeViewModel.setNotices()

        toolbar = binding.toolbarListNoteId as Toolbar
        toolbar.inflateMenu(R.menu.menu_toolbar_note_info)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        return binding.root
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


    override fun onLongClick() {
        val callbackList = object : ActionMode.Callback {
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                Log.d("tag", "action mode")
                mode?.menuInflater?.inflate(R.menu.menu_contextual_toolbar_list_note, menu)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                Log.d("tag", "action mode")
                return true
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                Log.d("tag", "action mode")
                return true
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                Log.d("tag", "action mode")
            }
        }
        (activity as AppCompatActivity).startSupportActionMode(callbackList)
        //toolbar.startActionMode(callbackList)
    }
}