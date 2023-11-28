package com.example.usecases

import com.example.data.NoteRepository
import com.example.domain.Note

class AddNote(private val noteRepository:NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}