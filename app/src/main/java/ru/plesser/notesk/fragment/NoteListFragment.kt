package ru.plesser.notesk.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.plesser.notesk.R
import ru.plesser.notesk.adapter.NoteAdapter
import ru.plesser.notesk.data.Data
import ru.plesser.notesk.data.Note

private const val TAG = "NoteListFragment"

class NoteListFragment : Fragment(), NoteAdapter.Listener {

    var data = Data.newInstance()
    lateinit var listener: Listener

    private lateinit var addNoteButton: ImageButton
    private lateinit var notesRecyclerView: RecyclerView
    private var adapter: NoteAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total notes: ${data.getNotes().size}")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note_list, container, false)
        notesRecyclerView = view.findViewById(R.id.note_recycler_view)
        notesRecyclerView.layoutManager = LinearLayoutManager(context)
        updateUI()

        addNoteButton = view.findViewById(R.id.add_note_button)
        addNoteButton.setOnClickListener(View.OnClickListener {
            Log.d(TAG, "pressed add note button")
            val note = Note("", "")
            listener.onClick(-1)
        })

        return view
    }

    private fun updateUI() {
        val notes = data.getNotes()
        adapter = NoteAdapter(notes, activity, this@NoteListFragment)
        notesRecyclerView.adapter = adapter

    }

    fun updateRV(id: Int){
        notesRecyclerView.scrollToPosition(id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



    companion object {
        fun newInstance(): NoteListFragment {
            return NoteListFragment()
        }
    }

    override fun onClick(id: Int) {
        listener.onClick(id)
    }

    interface Listener{
        fun onClick(id: Int)
        fun addNote()
    }
}