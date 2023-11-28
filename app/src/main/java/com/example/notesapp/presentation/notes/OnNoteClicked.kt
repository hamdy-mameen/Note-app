package com.example.notesapp.presentation.notes

import com.example.domain.Note

interface OnNoteClicked {
    fun onClicked(note:Note)
}