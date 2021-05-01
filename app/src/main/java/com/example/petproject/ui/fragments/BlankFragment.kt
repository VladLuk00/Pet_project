package com.example.petproject.ui.fragments
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.example.petproject.R
import com.example.petproject.data.pojo.Note
import com.example.petproject.databinding.FragmentBlankBinding
import com.example.petproject.ui.adapters.NoteRecycleViewAdapter
import com.example.petproject.ui.vm.NoticeViewModel
import dagger.hilt.android.AndroidEntryPoint

class BlankFragment : Fragment(R.layout.fragment_blank) {

    private val viewModel: NoticeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("tag", "fragment 44")
        /*viewModel.listOfNotices?.observe(viewLifecycleOwner, Observer {

        })*/
        /*val activityBinding: FragmentBlankBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false)

        activityBinding.lifecycleOwner = activity
        activityBinding.viewModel = viewModel

        val noteAdapter = NoteRecycleViewAdapter(viewModel)
        activityBinding.adapter = noteAdapter*/

        /*viewModel.insert(Note(title = "title", description = "description"))
        Log.d("tag", "${viewModel.listOfNotices}")*/
    }
}