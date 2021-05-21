package com.example.petproject.ui.notes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.petproject.R
import com.example.petproject.data.model.Note
import com.example.petproject.databinding.FragmentListNoteBinding
import com.example.petproject.ui.addNote.AddNoteActivity
import com.example.petproject.ui.noteInfo.NoteInfoActivity
import com.example.petproject.ui.noteInfo.NoteInfoFragment
import com.example.petproject.ui.noteInfo.NoteInfoFragmentDirections
import com.example.petproject.ui.notes.adapters.NewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.math.log


class NotesFragment : Fragment(R.layout.fragment_list_note) {

    private val noticeViewModel by activityViewModels<NoticeViewModel>()
    lateinit var binding: FragmentListNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("tag", "onCreateView")
        val notesAdapter = NewAdapter {
            /*val intent = Intent(context, NoteInfoFragment::class.java)
            intent.putExtra("description", it.description)
            intent.putExtra("title", it.title)
            intent.putExtra("id", it.noteId)
            Log.d("tag", it.description)
            startActivity(intent)*/

            /*val action = NotesFragmentDirections.actionNotesFragmentToGraphNoteInfo()
            view?.findNavController()?.navigate(action)*/
        }

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_list_note, container, false)
        binding.apply {
            viewModel = noticeViewModel
            lifecycleOwner = this@NotesFragment
            adapter = notesAdapter
        }

        /*CoroutineScope(Dispatchers.Default).launch {
            noticeViewModel.insert(Note(title = "title", description = "desc"))
            noticeViewModel.setNotices()
        }*/
        
        noticeViewModel.listOfNotices.observe(viewLifecycleOwner, Observer {
            it.let(notesAdapter::submitList)
        })

        val view = binding.root
        return view
    }

    fun onAddButtonClick(view: View) {
        startActivity(Intent(context, AddNoteActivity::class.java))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_list_note, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onStart() {
        super.onStart()
        CoroutineScope(Job()).launch(Dispatchers.Default) {
            noticeViewModel.setNotices()
            launch(Dispatchers.Main) {
                binding.adapter?.notifyDataSetChanged()
                Log.d("tag", "changes")
            }
        }
    }
}