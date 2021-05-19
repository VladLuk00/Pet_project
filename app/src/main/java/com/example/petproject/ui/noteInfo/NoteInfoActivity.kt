package com.example.petproject.ui.noteInfo

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.example.petproject.R
import com.example.petproject.ui.noteInfo.dialogs.NotificationDialog
import com.example.petproject.ui.notes.NoticeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NoteInfoActivity : AppCompatActivity() {

    private val viewModel by viewModels<NoticeViewModel>()

    lateinit var description: EditText
    lateinit var title: EditText
    var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_info)

        description = findViewById(R.id.noteInfoDescriptionId)
        title = findViewById(R.id.noteInfoTitleId)
        id = intent.getIntExtra("id", -1)

        description.text = SpannableStringBuilder(intent.getStringExtra("description"))
        title.text = SpannableStringBuilder(intent.getStringExtra("title"))

        val toolbar: Toolbar = findViewById(R.id.toolbarNoteInfo)
        setSupportActionBar(toolbar)
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.Default).launch {
            title.doAfterTextChanged { viewModel.updateTitle(it.toString(), id) }
            description.doAfterTextChanged { viewModel.updateDescription(it.toString(), id) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_note_info, menu)
        Log.d("tag", "menu create")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.a -> {
                Log.d("tag", "item selected")
                val notificationDialog = NotificationDialog()
                notificationDialog.show(supportFragmentManager, "notificationDialog")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}