package com.example.notesapp.framework.di

import android.content.Context
import androidx.room.Room
import com.example.notesapp.framework.db.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context)=
      Room.databaseBuilder(context,NoteDatabase::class.java,"note_db").build()

    @Provides
    @Singleton
    fun provideNoteDao(db:NoteDatabase) = db.getNoteDao()

   }