package com.example.notesapp.framework

import com.example.usecases.*
import javax.inject.Inject

data class Interactors @Inject constructor(
    val getNotes: GetAllNotes,
    val addNote: AddNote,
    val deleteNote: DeleteNote,
    val updateNote: UpdateNote
    )