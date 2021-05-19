package com.example.petproject.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.petproject.data.model.Label
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface LabelDao {

    @Query("SELECT * FROM label_table")
    fun getListLabels() : Single<List<Label>>
}