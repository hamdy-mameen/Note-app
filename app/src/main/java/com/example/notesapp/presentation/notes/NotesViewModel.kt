package com.example.notesapp.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Note
import com.example.notesapp.framework.Interactors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class NotesViewModel @Inject constructor(private val interActors: Interactors):ViewModel() {
    private val _notesState: MutableStateFlow<List<Note>> = MutableStateFlow(listOf())
    val notesState:StateFlow<List<Note>> =_notesState
    init {
        getAllNotes()
    }
   private fun getAllNotes(){
        viewModelScope.launch {
            interActors.getNotes().collect { notes ->
                _notesState.value= notes
            }
        }
    }
    fun addNote(note: Note){
        viewModelScope.launch {
         interActors.addNote(note)

        }
    }
    fun deleteNote(note: Note){
        viewModelScope.launch {
            interActors.deleteNote(note)
        }
    }
    fun updateNote(note: Note){
        viewModelScope.launch {
            interActors.updateNote(note)
        }
    }
}