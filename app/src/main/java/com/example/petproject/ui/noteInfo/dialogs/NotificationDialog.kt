package com.example.petproject.ui.noteInfo.dialogs

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.petproject.R
import com.example.petproject.data.model.Notification
import com.example.petproject.data.preferences.PreferencesHelper
import com.example.petproject.databinding.DialogFragmentNoteNotificationBinding
import com.example.petproject.utils.Constants
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.json.Json
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class NotificationDialog : DialogFragment() {

    @Inject lateinit var preferencesHelper: PreferencesHelper

    lateinit var binding: DialogFragmentNoteNotificationBinding

    var noteId: Int = 0
    lateinit var notification: Notification

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        noteId = requireArguments().getInt(Constants.NOTE_ID)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_fragment_note_notification, container, false)
        notification = Notification()
        setTime()
        setDate()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonCancelNotificationChanges.setOnClickListener { dismiss() }
        binding.buttonSaveNotificationChanges.setOnClickListener { dismiss() }
        binding.notificationTime.setOnClickListener { setTimePicker() }
        binding.notificationDate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance(TimeZone.getDefault())

            val dialog = DatePickerDialog(
                requireContext(), onDatePickListener(),
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            dialog.show()
        }
    }

    @SuppressLint("SetTextI18n")
    fun onDatePickListener() =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            notification.month = monthOfYear
            notification.year = year
            notification.day = dayOfMonth

            preferencesHelper.setNotifications(
                id = noteId,
                settings = notification
            )
            binding.notificationDate.text = "${notification.year}.${notification.month}.${notification.day}"
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
            notification.hour = picker.hour
            notification.minute = picker.minute

            preferencesHelper.setNotifications(
                id = noteId,
                settings = notification
            )
            binding.notificationTime.text = "${notification.hour}:${notification.minute}"
        }
    }

    @SuppressLint("SetTextI18n")
    fun setTime() {
        if (preferencesHelper.prefs.contains(noteId.toString())) {
            val notificationData = preferencesHelper.getNotifications(id = noteId)
            notification = Json.decodeFromString(Notification.serializer(), notificationData!!)

            binding.notificationTime.text = "${notification.hour}:${notification.minute}"
            Log.d("tag", preferencesHelper.toString())
        }
    }

    @SuppressLint("SetTextI18n")
    fun setDate() {
        if (preferencesHelper.prefs.contains(noteId.toString())) {
            val notificationData = preferencesHelper.getNotifications(id = noteId)
            notification = Json.decodeFromString(Notification.serializer(), notificationData!!)

            binding.notificationDate.text = "${notification.year}.${notification.month}.${notification.day}"
        }
    }
}