package com.example.notesapp.framework.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val description:String,
    val date:Long
)
