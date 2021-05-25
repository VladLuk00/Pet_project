package com.example.petproject.ui.notes

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NotesFragmentTest : TestCase() {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    /*@Inject
    lateinit var notesViewModel: NoticeViewModel

    @Before
    fun init() {
        hiltRule.inject()
    }*/

    @Test
    fun onRecycleViewItemClick() {

    }
}