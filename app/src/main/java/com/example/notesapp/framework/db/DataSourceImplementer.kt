package com.example.notesapp.framework.db

import com.example.data.NoteDataSource
import com.example.domain.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataSourceImplementer @Inject constructor(private val dao: NoteDao):NoteDataSource {
    override suspend fun addNote(note: Note) {
        dao.addNote(NoteEntity(id=note.id,title = note.title, description = note.description, date = note.date))
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(NoteEntity(id=note.id,title = note.title, description = note.description, date = note.date))
    }

    override suspend fun updateNote(note: Note) {
        dao.updateNote(NoteEntity(id=note.id,title = note.title, description = note.description, date = note.date))
    }
        override fun getAllNotes(): Flow<List<Note>> {
        return dao.getAllNotes().map { listEntity->listEntity.map { noteEntity -> Note(noteEntity.id,noteEntity.title,noteEntity.description,noteEntity.date) } }
    }

}