package com.example.notesapp.framework.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  addNote(note: NoteEntity)
    @Update
    suspend fun updateNote(note: NoteEntity)
    @Delete
    suspend fun deleteNote(note: NoteEntity)
    @Query("SELECT * FROM note_table")
    fun getAllNotes(): Flow<List<NoteEntity>>
}