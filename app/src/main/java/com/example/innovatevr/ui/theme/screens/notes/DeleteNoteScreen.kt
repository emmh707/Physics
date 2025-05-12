package com.example.innovatevr.ui.theme.screens

import android.content.Context
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.innovatevr.data.NoteViewModel

@Composable
fun DeleteNoteScreen(
    noteId: String,
    navController: NavController,
    noteViewModel: NoteViewModel,
    onDismiss: () -> Unit,
    context: Context
) {
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                noteViewModel.deleteNote(
                    context, noteId,
                    navController = TODO()
                )
                onDismiss()
                navController.popBackStack()
            }) {
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("No")
            }
        },
        title = { Text("Delete Note") },
        text = { Text("Are you sure you want to delete this note?") }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DeleteNoteScreenPreview() {
    val navController = rememberNavController()
    val noteViewModel = NoteViewModel()

    MaterialTheme {
        DeleteNoteScreen(
            noteId = "sampleNoteId",
            navController = navController,
            noteViewModel = noteViewModel,
            onDismiss = {},
            context = LocalContext.current
        )
    }
}