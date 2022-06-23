package ru.plesser.notesk.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import ru.plesser.notesk.R
import ru.plesser.notesk.data.Data
import ru.plesser.notesk.data.Note
import ru.plesser.notesk.utils.TimeManager
private const val TAG = "NoteAdapter"

class NoteAdapter(var notes: List<Note>, val activity: FragmentActivity?, val listener: Listener): RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val layoutInflater = LayoutInflater.from(activity)
        val view = layoutInflater.inflate(R.layout.item_note, parent, false)
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val note = notes.get(position)
        holder.bind(note, listener)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    public class NoteHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{

        private lateinit var note: Note
        private lateinit var listener: Listener

        init {
            itemView.setOnClickListener(this)
        }


        var callback: Listener? = null
        val noteTextView: TextView = itemView.findViewById(R.id.note_textview)
        val dateTextView: TextView = itemView.findViewById(R.id.date_textview)

        fun bind(note: Note, listener: Listener){
            this.note = note
            this.listener = listener

            note.apply {
                noteTextView.text = note.note
                dateTextView.text = TimeManager.getTime(note.date)
            }
        }

        override fun onClick(view: View?) {
            Log.d(TAG, note.note)
            val id = Data.newInstance().getNotes().indexOf(note)
            listener.onClick(id)
        }

    }

    interface Listener{
        fun onClick(id: Int)
    }
}