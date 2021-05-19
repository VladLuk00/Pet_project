package com.example.petproject.ui.noteInfo.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.petproject.R

class NotificationDialog : DialogFragment() {

    lateinit var saveChanges: Button
    lateinit var cancelChanges: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_note_notification, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveChanges = view.findViewById(R.id.buttonSaveNotificationChanges)
        cancelChanges = view.findViewById(R.id.buttonCancelNotificationChanges)

        cancelChanges.setOnClickListener { dismiss() }
    }
}