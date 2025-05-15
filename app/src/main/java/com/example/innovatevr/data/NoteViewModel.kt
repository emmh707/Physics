package com.example.innovatevr.data

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.innovatevr.models.NoteModel
import com.google.firebase.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class NoteViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance().reference.child("Notes")

    fun addNote(
        context: Context,
        title: String,
        content: String,
        onSuccess: () -> Unit = {}
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val noteId = database.push().key ?: return@launch
            val note = NoteModel(noteId = noteId, title = title, content = content)

            database.child(noteId).setValue(note)
                .addOnSuccessListener {
                    viewModelScope.launch(Dispatchers.Main) {
                        Toast.makeText(context, "Note saved successfully", Toast.LENGTH_SHORT).show()
                        onSuccess()
                    }
                }
                .addOnFailureListener {
                    viewModelScope.launch(Dispatchers.Main) {
                        Toast.makeText(context, "Failed to save note", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    open fun viewNotes(
        note: MutableState<NoteModel>,
        notes: SnapshotStateList<NoteModel>,
        context: Context
    ) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                viewModelScope.launch(Dispatchers.Main) {
                    notes.clear()
                    for (snap in snapshot.children) {
                        val item = snap.getValue(NoteModel::class.java)
                        item?.let { notes.add(it) }
                    }
                    if (notes.isNotEmpty()) {
                        note.value = notes.first()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Failed to fetch notes", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun updateNote(
        context: Context,
        title: String,
        content: String,
        noteId: String,
        onSuccess: () -> Unit = {}
    ) {
        val ref = database.child(noteId)
        val updatedNote = NoteModel(noteId = noteId, title = title, content = content)

        ref.setValue(updatedNote).addOnCompleteListener { task ->
            Toast.makeText(
                context,
                if (task.isSuccessful) "Note updated" else "Update failed",
                Toast.LENGTH_SHORT
            ).show()

            if (task.isSuccessful) onSuccess()
        }
    }

    fun deleteNote(
        noteId: String,
        context: Context,
        onSuccess: () -> Unit = {}
    ) {
        database.child(noteId).removeValue().addOnCompleteListener { task ->
            Toast.makeText(
                context,
                if (task.isSuccessful) "Note deleted" else "Delete failed",
                Toast.LENGTH_SHORT
            ).show()

            if (task.isSuccessful) onSuccess()
        }
    }
}