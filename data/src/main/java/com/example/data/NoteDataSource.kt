package com.example.data

import com.example.domain.Note
import kotlinx.coroutines.flow.Flow


interface NoteDataSource {
    suspend fun addNote(note: Note)
    suspend fun deleteNote(note:Note)
    suspend fun updateNote(note: Note)
    fun getAllNotes():Flow<List<Note>>
}