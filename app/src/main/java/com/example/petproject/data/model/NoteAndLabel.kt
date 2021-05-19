package com.example.petproject.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class NoteAndLabel (
    @Embedded val label: Label,
    @Relation(
        parentColumn = "labelId",
        entityColumn = "labelAttachedId"
    )
    val note: Note
)
