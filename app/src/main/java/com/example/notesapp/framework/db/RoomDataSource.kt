package com.example.notesapp.framework.db

import com.example.data.LocalDataSource
import com.example.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomDataSource @Inject constructor(private val dao: NoteDao):LocalDataSource {
    override suspend fun addNote(note: Note) {
        dao.addNote(note.toNoteEntity())
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note.toNoteEntity())
    }

    override suspend fun updateNote(note: Note) {
        dao.updateNote(note.toNoteEntity())
    }
        override fun getAllNotes(): Flow<List<Note>> {
        return dao.getAllNotes().map { listEntity->listEntity.map { noteEntity -> Note(noteEntity.id,noteEntity.title,noteEntity.description,noteEntity.date) } }
    }
     private fun Note.toNoteEntity()=
         NoteEntity(
             id =id,
             title=title,
             description=description,
             date =date
         )

}