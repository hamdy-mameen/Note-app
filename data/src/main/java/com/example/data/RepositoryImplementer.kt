package com.example.data

import com.example.domain.model.Note
import com.example.domain.usecases.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImplementer @Inject constructor (private val dataSource: LocalDataSource):NoteRepository{
    override suspend fun addNote(note: Note) {
        dataSource.addNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dataSource.deleteNote(note)
    }

    override suspend fun updateNote(note: Note) {
        dataSource.updateNote(note)
    }

    override fun getAllNotes(): Flow<List<Note>> = dataSource.getAllNotes()

}