package com.example.innovatevr.data

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
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
        navController: NavController
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val noteId = database.push().key ?: return@launch
            val note = NoteModel(noteId, title, content)

            database.child(noteId).setValue(note)
                .addOnSuccessListener {
                    viewModelScope.launch(Dispatchers.Main) {
                        Toast.makeText(context, "Note saved successfully", Toast.LENGTH_SHORT).show()
                        // navController.navigate(ROUTE_VIEW_NOTES) if needed
                    }
                }.addOnFailureListener {
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
    ): SnapshotStateList<NoteModel> {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                notes.clear()
                for (snap in snapshot.children) {
                    val item = snap.getValue(NoteModel::class.java)
                    item?.let { notes.add(it) }
                }
                if (notes.isNotEmpty()) {
                    note.value = notes.first()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Failed to fetch notes", Toast.LENGTH_SHORT).show()
            }
        })
        return notes
    }

    fun updateNote(
        context: Context,
        navController: NavController,
        title: String,
        content: String,
        noteId: String
    ) {
        val ref = database.child(noteId)
        val updatedNote = NoteModel(noteId, title, content)

        ref.setValue(updatedNote).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Note updated", Toast.LENGTH_SHORT).show()
                // navController.navigate(ROUTE_VIEW_NOTES)
            } else {
                Toast.makeText(context, "Update failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun deleteNote(
        context: Context,
        noteId: String,
        navController: NavController
    ) {
        AlertDialog.Builder(context)
            .setTitle("Delete Note")
            .setMessage("Are you sure you want to delete this note?")
            .setPositiveButton("Yes") { _, _ ->
                database.child(noteId).removeValue().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Delete failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
