package com.example.petproject.ui.noteInfo.dialogs

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.petproject.R
import com.example.petproject.data.preferences.PreferencesHelper
import com.example.petproject.databinding.DialogFragmentNoteNotificationBinding
import com.example.petproject.ui.base.BaseFragmentDialog
import com.example.petproject.utils.Constants
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class NotificationDialog(
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
        DialogFragmentNoteNotificationBinding = DialogFragmentNoteNotificationBinding::inflate
) :
    BaseFragmentDialog<DialogFragmentNoteNotificationBinding>() {

    @Inject lateinit var preferencesHelper: PreferencesHelper
    private val viewModel by activityViewModels<NotificationDialogViewModel>()

    var noteId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        noteId = requireArguments().getInt(Constants.NOTE_ID)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            buttonCancelNotificationChanges.setOnClickListener { dismiss() }
            buttonSaveNotificationChanges.setOnClickListener { dismiss() }
            notificationMap.setOnClickListener { findNavController().navigate(R.id.to_mapsFragment)}
            notificationTime.setOnClickListener { setTimePicker() }
            notificationDate.setOnClickListener {
                val calendar: Calendar = Calendar.getInstance(TimeZone.getDefault())

                val dialog = DatePickerDialog(
                    requireContext(), onDatePickListener(),
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                dialog.show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun onDatePickListener() =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            viewModel.updateDate(
                year = year,
                month = monthOfYear,
                day = dayOfMonth,
                id = noteId
            )

            binding.executePendingBindings()
        }

    @SuppressLint("SetTextI18n")
    fun setTimePicker() {
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(10)
            .build()
        picker.show(childFragmentManager, "")
        picker.addOnPositiveButtonClickListener {
            viewModel.updateTime(
                hour = picker.hour,
                minute = picker.minute,
                id = noteId
            )
            binding.executePendingBindings()
        }
    }

    override fun init() {
        binding.apply {
            lifecycleOwner = this@NotificationDialog
            viewModel = this@NotificationDialog.viewModel
        }
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.setNotification(noteId)
        }
        Log.d("tag", "init")
    }
}