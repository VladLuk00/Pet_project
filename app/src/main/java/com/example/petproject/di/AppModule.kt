package com.example.petproject.di

import android.content.Context
import androidx.room.Room
import com.example.petproject.data.db.NotesDatabase
import com.example.petproject.data.model.Label
import com.example.petproject.ui.directories.view.adapters.RecycleViewLabelAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, NotesDatabase::class.java, "note_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideNoteDao(notesDatabase: NotesDatabase) = notesDatabase.noteDao()

    @Provides
    fun provideLabelDao(notesDatabase: NotesDatabase) = notesDatabase.labelDao()

    @Provides
    @Singleton
    fun provideRecycleViewLabelAdapter() = RecycleViewLabelAdapter(listOf(Label(name = "di")))

    @Provides
    fun provideContext(@ApplicationContext context: Context) : Context = context

}