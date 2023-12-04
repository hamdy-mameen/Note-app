package com.example.data

import com.example.domain.model.Note
import kotlinx.coroutines.flow.Flow


interface LocalDataSource {
    suspend fun addNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun updateNote(note: Note)
    fun getAllNotes():Flow<List<Note>>
}