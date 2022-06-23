package ru.plesser.notesk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.plesser.notesk.data.Data
import ru.plesser.notesk.data.Note
import ru.plesser.notesk.fragment.NoteFragment
import ru.plesser.notesk.fragment.NoteListFragment

private const val TAG = "NotesActivity"

class NotesActivity : AppCompatActivity(), NoteListFragment.Listener, NoteFragment.Listener {

    lateinit var fragment: NoteListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null){
            fragment = NoteListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .addToBackStack("list_notes")
                .commit()
            fragment.listener = this@NotesActivity
        }
    }

    override fun onClick(id: Int) {
        val fragment = NoteFragment.newInstance(id)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack("note")
            .commit()
        fragment.listener = this@NotesActivity
    }

    override fun addNote() {
        TODO("Not yet implemented")
    }

    override fun updateRC(id: Int) {
        Log.d(TAG, "update RecyclerView")
        fragment.updateRV(id)
    }
}