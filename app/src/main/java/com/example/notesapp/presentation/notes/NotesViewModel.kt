package com.example.notesapp.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Note
import com.example.domain.usecases.ManageNoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class NotesViewModel @Inject constructor(private val useCases: ManageNoteUseCases):ViewModel() {
    private val _notesState: MutableStateFlow<List<Note>> = MutableStateFlow(listOf())
    val notesState:StateFlow<List<Note>> =_notesState
    init {
        getAllNotes()
    }
   private fun getAllNotes(){
        viewModelScope.launch {
            useCases.getALLNotes().collect { notes ->
                _notesState.value= notes
            }
        }
    }
    fun addNote(note: Note){
        viewModelScope.launch {
         useCases.addNote(note)

        }
    }
    fun deleteNote(note: Note){
        viewModelScope.launch {
            useCases.deleteNote(note)
        }
    }
    fun updateNote(note: Note){
        viewModelScope.launch {
            useCases.updateNote(note)
        }
    }
}