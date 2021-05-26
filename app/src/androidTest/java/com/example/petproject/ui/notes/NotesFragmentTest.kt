package com.example.petproject.ui.notes

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class NotesFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var notesViewModel: NotesViewModel

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun onRecycleViewItemClick() {

    }
}