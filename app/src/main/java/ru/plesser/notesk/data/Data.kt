package ru.plesser.notesk.data

import java.util.ArrayList

class Data {
    private var data = ArrayList<Note>()

    init {
        for (i in 0 until 10){
            val note = Note("Заметка# $i", "Описание заметки $i")
            addNote(note)
        }
    }

    fun getNotes() = data

    fun addNote(note: String, desc: String){
        addNote(Note(note, desc))
    }

    fun addNote(note: Note){
        data.add(note)
    }

    fun getNote(i: Int) : Note? {
        if (i < data.size){
            return data[i]
        }
        return null
    }


    companion object{
        private var data: Data

        init {
            data = Data()
        }

        fun newInstance() : Data{
            return data
        }
    }

}