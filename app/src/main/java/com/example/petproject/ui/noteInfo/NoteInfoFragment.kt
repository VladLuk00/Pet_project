package com.example.petproject.ui.noteInfo

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.petproject.GraphNoteInfoDirections
import com.example.petproject.R
import com.example.petproject.ui.noteInfo.dialogs.NotificationDialog
import com.example.petproject.ui.notes.NotesFragmentArgs
import com.example.petproject.ui.notes.NotesFragmentDirections
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
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_note_info, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onResume() {
        super.onResume()
        Log.d("tag", "onResume")
        CoroutineScope(Dispatchers.Default).launch {
            title.doAfterTextChanged { viewModel.updateTitle(it.toString(), noteId) }
            description.doAfterTextChanged { viewModel.updateDescription(it.toString(), noteId) }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.notification -> {
                val notificationDialog = NotificationDialog()
                //notificationDialog.show(supportFragmentManager, "notificationDialog")
                true
            }
            R.id.color -> {
                Log.d("tag", "color")
                val colorPicker = ColorPicker(NoteInfoActivity())
                colorPicker.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}