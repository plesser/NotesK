package ru.plesser.notesk.data

import java.io.Serializable
import java.util.*

data class Note(var note: String, var description: String){
    var date: Date = Calendar.getInstance().time
}