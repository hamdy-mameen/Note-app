package com.example.notesapp.presentation.notes

import com.example.domain.model.Note

interface OnNoteClicked {
    fun onClicked(note: Note)
}