package com.example.notesapp.framework.di

import com.example.data.NoteDataSource
import com.example.data.NoteRepository
import com.example.notesapp.framework.db.DataSourceImplementer
import com.example.notesapp.framework.db.NoteDao
import com.example.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {
    @Provides
    fun provideNoteDataSource(dao: NoteDao): NoteDataSource = DataSourceImplementer(dao)

    @Provides
    fun provideNoteRepository(source: NoteDataSource) = NoteRepository(source)

    @Provides
    fun provideAddNoteUseCase(repo: NoteRepository) = AddNote(repo)

    @Provides
    fun provideDeleteNoteUseCase(repo: NoteRepository) = DeleteNote(repo)

    @Provides
    fun provideUpdateNoteUseCase(repo: NoteRepository) = UpdateNote(repo)

    @Provides
    fun provideGetAllNotesUseCase(repo: NoteRepository) = GetAllNotes(repo)
}