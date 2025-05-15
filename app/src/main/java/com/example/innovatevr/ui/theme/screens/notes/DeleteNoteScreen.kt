package com.example.innovatevr.ui.theme.screens.notes

import android.widget.Toast
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.innovatevr.data.NoteViewModel

@Composable
fun DeleteNoteScreen(
    noteId: String,
    navController: NavController,
    noteViewModel: NoteViewModel,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete Note") },
        text = { Text("Are you sure you want to delete this note?") },
        confirmButton = {
            TextButton(onClick = {
                noteViewModel.deleteNote(
                    context = context,
                    noteId = noteId
                ) {
                    Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
                    onDismiss()
                }
            }) {
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("No")
            }
        }
    )
}