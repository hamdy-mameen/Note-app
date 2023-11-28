package com.example.domain

import java.io.Serializable

data class Note(
    val id:Int=0,
    val title:String,
    val description:String,
    val date:Long
): Serializable
