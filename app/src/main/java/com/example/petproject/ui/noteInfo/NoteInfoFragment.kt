package com.example.petproject.ui.noteInfo

import android.graphics.drawable.ColorDrawable
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
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import com.example.petproject.R
import com.example.petproject.data.preferences.PreferencesHelper
import com.example.petproject.ui.notes.NotesFragmentArgs
import com.example.petproject.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import petrov.kristiyan.colorpicker.ColorPicker
import javax.inject.Inject

@AndroidEntryPoint
class NoteInfoFragment : Fragment(R.layout.fragment_note_info) {

    private val viewModel by activityViewModels<NoteInfoViewModel>()

    lateinit var toolbar: Toolbar

    lateinit var description: EditText
    lateinit var title: EditText
    @Inject lateinit var preferencesHelper: PreferencesHelper

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

        toolbar = view.findViewById(R.id.toolbarNoteInfo)
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
                findNavController().navigate(R.id.action_noteInfoFragment_to_notificationDialog, bundleOf(Constants.NOTE_ID to noteId))

                //findNavController().navigate(R.id.action_noteInfoFragment_to_timePickerDialogFragment2)
                true
            }
            R.id.color -> {
                Log.d("tag", "color")
                val colorPicker = ColorPicker(activity)
                colorPicker.show()
                colorPicker.setOnChooseColorListener(object : ColorPicker.OnChooseColorListener {
                    override fun onChooseColor(position: Int, color: Int) {
                        toolbar.background = ColorDrawable(color)
                    }

                    override fun onCancel() {
                        TODO("Not yet implemented")
                    }
                })
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}