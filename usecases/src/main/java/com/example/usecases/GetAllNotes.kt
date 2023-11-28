package com.example.usecases

import com.example.data.NoteRepository

class GetAllNotes(private val noteRepository: NoteRepository) {
    operator fun invoke() = noteRepository.getAllNotes()
}