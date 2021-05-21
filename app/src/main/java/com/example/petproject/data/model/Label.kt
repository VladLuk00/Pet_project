package com.example.petproject.data.model

import android.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "label_table")
data class Label(
    @PrimaryKey(autoGenerate = true) val labelId: Int = 0,
    @ColumnInfo(name = "name")val name: String = "",
    @ColumnInfo(name = "color") val color: Int? = 0)