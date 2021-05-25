package com.example.petproject.ui.notes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.MediaStoreSignature
import com.bumptech.glide.signature.ObjectKey
import com.example.petproject.GraphNoteInfoDirections
import com.example.petproject.R
import com.example.petproject.data.model.Note
import com.example.petproject.databinding.FragmentListNoteBinding
import com.example.petproject.ui.addNote.AddNoteActivity
import com.example.petproject.ui.noteInfo.NoteInfoActivity
import com.example.petproject.ui.noteInfo.NoteInfoFragment
import com.example.petproject.ui.noteInfo.NoteInfoFragmentDirections
import com.example.petproject.ui.notes.adapters.NewAdapter
import com.example.petproject.ui.notes.binding.MyHandler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.math.log


@AndroidEntryPoint
class NotesFragment : Fragment(R.layout.fragment_list_note) {

    val noticeViewModel by hiltNavGraphViewModels<NoticeViewModel>(R.id.nav_graph)
    lateinit var binding: FragmentListNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //setBackgroundImage()

        val notesAdapter = NewAdapter {
            /*val intent = Intent(context, NoteInfoFragment::class.java)
            intent.putExtra("description", it.description)
            intent.putExtra("title", it.title)
            intent.putExtra("id", it.noteId)
            Log.d("tag", it.description)
            startActivity(intent)*/
            val bundle: Bundle = Bundle()
            bundle.putString("description", it.description)
            bundle.putString("title", it.title)
            bundle.putInt("id", it.noteId)
            //findNavController().setGraph(R.navigation.graph_note_info, bundle)
            view?.findNavController()?.navigate(R.id.graph_note_info, bundle)
        }

        val myhandler = MyHandler()

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_list_note, container, false)
        binding.apply {
            viewModel = noticeViewModel
            lifecycleOwner = this@NotesFragment
            adapter = notesAdapter
            handler = myhandler
        }

        /*CoroutineScope(Dispatchers.Default).launch {
            noticeViewModel.insert(Note(title = "title", description = "desc"))
            noticeViewModel.setNotices()
        }*/
        
        noticeViewModel.listOfNotices.observe(viewLifecycleOwner, Observer {
            it.let(notesAdapter::submitList)
        })

        noticeViewModel.setNotices()

        val view = binding.root
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_list_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setBackgroundImage() {
        val imageContainer : ImageView? = binding.root.findViewById(R.id.pictureContainer)
        val url = "https://picsum.photos/${imageContainer?.width}/${imageContainer?.height}/?random"
        Glide.with(this)
            .load(url)
            .into(imageContainer!!)
    }
}