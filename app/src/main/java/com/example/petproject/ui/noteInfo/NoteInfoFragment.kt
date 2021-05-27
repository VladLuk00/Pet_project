package com.example.petproject.ui.noteInfo

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import com.example.petproject.R
import com.example.petproject.ui.main.MainActivity
import com.example.petproject.ui.noteInfo.dialogs.NotificationDialog
import com.example.petproject.ui.notes.NotesFragmentArgs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import petrov.kristiyan.colorpicker.ColorPicker


class NoteInfoFragment : Fragment(R.layout.fragment_note_info) {

    private val viewModel by activityViewModels<NoteInfoViewModel>()

    lateinit var description: EditText
    lateinit var title: EditText
    var noteId: Int = 0
    val args by navArgs<NotesFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        description = view.findViewById(R.id.noteInfoDescriptionId)
        title = view.findViewById(R.id.noteInfoTitleId)
        noteId = arguments?.getInt("id")!!
        description.text = SpannableStringBuilder(arguments?.getString("description"))
        title.text = SpannableStringBuilder(arguments?.getString("title"))
        val configuration = AppBarConfiguration(findNavController().graph)

        val toolbar: Toolbar = view.findViewById(R.id.toolbarNoteInfo)
        //toolbar.inflateMenu(R.menu.menu_toolbar_note_info)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view.post { findNavController().popBackStack() }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_note_info, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.Default).launch {
            title.doAfterTextChanged { viewModel.updateTitle(it.toString(), noteId) }
            description.doAfterTextChanged { viewModel.updateDescription(it.toString(), noteId) }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("tag", "menu")
        return when (item.itemId) {
            R.id.notification -> {
                findNavController().navigate(R.id.action_noteInfoFragment_to_notificationDialog)
                true
            }
            R.id.color -> {
                Log.d("tag", "color")
                val colorPicker = ColorPicker(activity)
                colorPicker.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}