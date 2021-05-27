package com.example.petproject.utils

import com.example.petproject.data.model.Note

object CustomMockUtil {

    fun getNote() = Note(description = "desc", title = "title")

    fun getListOfNotes() = listOf(getNote())
}