package com.example.petproject.ui.noteInfo

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import com.example.petproject.R
import com.example.petproject.data.preferences.PreferencesHelper
import com.example.petproject.databinding.FragmentNoteInfoBinding
import com.example.petproject.ui.base.BaseFragment
import com.example.petproject.ui.notes.NotesFragmentArgs
import com.example.petproject.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import petrov.kristiyan.colorpicker.ColorPicker
import javax.inject.Inject

@AndroidEntryPoint
class NoteInfoFragment(
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentNoteInfoBinding = FragmentNoteInfoBinding::inflate
) :
    BaseFragment<FragmentNoteInfoBinding>() {

    private val viewModel by activityViewModels<NoteInfoViewModel>()

    @Inject
    lateinit var preferencesHelper: PreferencesHelper

    var noteId: Int = 0
    val args by navArgs<NotesFragmentArgs>()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_note_info, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.Default).launch {
            binding.noteInfo.noteInfoTitleId.doAfterTextChanged { viewModel.updateTitle(it.toString(), noteId) }
            binding.noteInfo.noteInfoDescriptionId.doAfterTextChanged { viewModel.updateDescription(it.toString(), noteId) }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.notification -> {
                findNavController().navigate(
                    R.id.action_noteInfoFragment_to_notificationDialog,
                    bundleOf(Constants.NOTE_ID to noteId)
                )

                //findNavController().navigate(R.id.action_noteInfoFragment_to_timePickerDialogFragment2)
                true
            }
            R.id.color -> {
                Log.d("tag", "color")
                val colorPicker = ColorPicker(activity)
                colorPicker.show()
                colorPicker.setOnChooseColorListener(object : ColorPicker.OnChooseColorListener {
                    override fun onChooseColor(position: Int, color: Int) {
                        binding.toolbarNoteInfo.background = ColorDrawable(color)
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

    override fun init() {
        noteId = arguments?.getInt("id")!!
        binding.noteInfo.noteInfoDescriptionId.text = SpannableStringBuilder(arguments?.getString("description"))
        binding.noteInfo.noteInfoTitleId.text = SpannableStringBuilder(arguments?.getString("title"))
        val configuration = AppBarConfiguration(findNavController().graph)

        //toolbar.inflateMenu(R.menu.menu_toolbar_note_info)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarNoteInfo as Toolbar)
        setHasOptionsMenu(true)

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view?.post { findNavController().popBackStack() }
            }
        })
    }
}