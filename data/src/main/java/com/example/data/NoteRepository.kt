package com.example.data

import com.example.domain.Note

class NoteRepository(private val dataSource: NoteDataSource) {
    suspend fun addNote(note: Note) = dataSource.addNote(note)
    suspend fun updateNote(note: Note)=dataSource.updateNote(note)
    suspend fun deleteNote(note: Note)=dataSource.deleteNote(note)
    fun getAllNotes() = dataSource.getAllNotes()
}