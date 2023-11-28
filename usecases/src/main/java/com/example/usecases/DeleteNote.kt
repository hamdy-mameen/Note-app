package com.example.usecases

import com.example.data.NoteRepository
import com.example.domain.Note

class DeleteNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note:Note) = noteRepository.deleteNote(note)
}