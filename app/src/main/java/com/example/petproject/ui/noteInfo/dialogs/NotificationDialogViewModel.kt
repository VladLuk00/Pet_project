package com.example.petproject.ui.noteInfo.dialogs

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petproject.data.db.NoteDao
import com.example.petproject.data.model.Note
import com.example.petproject.data.model.Notification
import com.example.petproject.data.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.StringBuilder
import javax.inject.Inject

@HiltViewModel
class NotificationDialogViewModel @Inject constructor(val notesRepository: NotesRepository) :
    ViewModel() {

    private val notification = MutableLiveData<Notification?>()

    private val _date = MutableLiveData<String>(notification.value?.dateString())
    private val _time = MutableLiveData<String>(notification.value?.timeString())

    val date = _date
    val time = _time

    fun updateDate(year: Int, month: Int, day: Int, id: Int) {
        CoroutineScope(Dispatchers.Default).launch {
            notesRepository.updateDate(year, month, day, id)

            launch(Dispatchers.Main) {
                setNotification(id)
                _date.value = notification.value?.dateString()
            }
        }
    }

    fun updateTime(hour: Int, minute: Int, id: Int) {
        CoroutineScope(Dispatchers.Default).launch {
            notesRepository.updateTime(hour, minute, id)

            launch(Dispatchers.Main) {
                setNotification(id)
                _time.value = notification.value?.timeString()
            }
        }
    }

    suspend fun setNotification(id: Int) {
        notification.value = notesRepository.getNote(id).notification
        Log.d("tag", "setNot")
    }


}