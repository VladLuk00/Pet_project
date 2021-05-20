package com.example.petproject.ui.noteInfo

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.petproject.R
import com.example.petproject.ui.notes.NotesFragmentArgs
import com.example.petproject.ui.notes.NotesFragmentDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteInfoFragment : Fragment(R.layout.fragment_note_info) {

    private val viewModel by activityViewModels<NoteInfoViewModel>()

    lateinit var description: EditText
    //lateinit var title: EditText
    //var noteId: Int = 0
    val args by navArgs<NotesFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        description = view.findViewById(R.id.noteInfoDescriptionId)
        //title = view.findViewById(R.id.noteInfoTitleId)
        //noteId = arguments?.getInt("id", -1)
        description.text = SpannableStringBuilder(args.noteArg)
        //title.text = SpannableStringBuilder(arguments?.getString("title"))

    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.Default).launch {
            //title.doAfterTextChanged { viewModel.updateTitle(it.toString(), id) }
            description.doAfterTextChanged { viewModel.updateDescription(it.toString(), id) }
        }
    }
}