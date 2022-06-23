package ru.plesser.notesk.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import ru.plesser.notesk.R
import ru.plesser.notesk.data.Data
import ru.plesser.notesk.data.Note

class NoteFragment: Fragment() {

    lateinit var note: Note

    lateinit var noteEditText: EditText
    lateinit var descEditText: EditText
    lateinit var saveButton: Button

    lateinit var listener: Listener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id: Int? = arguments?.getInt(NOTE)
        if (id != -1) {
            note = Data.newInstance().getNote(id!!)!!
        } else {
            note = Note("", "")
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note, container, false)

        noteEditText = view.findViewById(R.id.note_edittext)
        descEditText = view.findViewById(R.id.description_edittext)

        noteEditText.setText(note.note)
        descEditText.setText(note.description)

        saveButton = view.findViewById(R.id.save_button)
        saveButton.setOnClickListener(View.OnClickListener {
            note.note = noteEditText.text.toString()
            note.description = noteEditText.text.toString()

            if (Data.newInstance().getNotes().indexOf(note) == -1){
                Data.newInstance().addNote(note)
            }

            this@NoteFragment.activity?.onBackPressed()
            listener.updateRC(Data.newInstance().getNotes().indexOf(note))
        })



        return view

    }

    interface Listener{
        fun updateRC(id: Int)
    }



    companion object {

        const val NOTE = "note.id"

        fun newInstance(id: Int): NoteFragment {
            val fragment : NoteFragment =NoteFragment()
            val args: Bundle = Bundle()
            args.putInt(NOTE, id)
            fragment.arguments = args
            return fragment
        }
    }


}
