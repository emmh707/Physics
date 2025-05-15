package com.example.innovatevr.ui.theme.screens.notes

import com.example.innovatevr.ui.theme.screens.notes.DeleteNoteScreen
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.innovatevr.data.NoteViewModel
import com.example.innovatevr.models.NoteModel

@Composable
fun ManageNotesScreen(navController: NavController, noteViewModel: NoteViewModel) {
    val context = LocalContext.current
    val notes = remember { mutableStateListOf<NoteModel>() }
    val currentNote = remember { mutableStateOf(NoteModel()) }

    val showDeleteDialog = remember { mutableStateOf(false) }
    val noteToDelete = remember { mutableStateOf<NoteModel?>(null) }

    // Load notes from ViewModel
    LaunchedEffect(Unit) {
        noteViewModel.viewNotes(currentNote, notes, context)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Text("Saved Notes", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(notes) { note ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = note.title, style = MaterialTheme.typography.titleMedium)
                        Text(text = note.content, style = MaterialTheme.typography.bodyMedium)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(onClick = {
                                val encodedTitle = Uri.encode(note.title)
                                val encodedContent = Uri.encode(note.content)
                                navController.navigate("update_note/${note.noteId}/$encodedTitle/$encodedContent")
                            }) {
                                Text("Edit")
                            }

                            TextButton(onClick = {
                                noteToDelete.value = note
                                showDeleteDialog.value = true
                            }) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }

        // Show delete dialog
        noteToDelete.value?.let { note ->
            if (showDeleteDialog.value) {
                DeleteNoteScreen(
                    noteId = note.noteId,
                    navController = navController,
                    noteViewModel = noteViewModel,
                    onDismiss = {
                        showDeleteDialog.value = false
                        noteToDelete.value = null
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ManageNotesScreenPreview() {
    val navController = rememberNavController()

    val mockViewModel = object : NoteViewModel() {
        override fun viewNotes(
            note: MutableState<NoteModel>,
            notes: SnapshotStateList<NoteModel>,
            context: android.content.Context
        ) {
            notes.clear()
            notes.addAll(
                listOf(
                    NoteModel(noteId = "1", title = "Sample 1", content = "First note."),
                    NoteModel(noteId = "2", title = "Sample 2", content = "Second note.")
                )
            )
        }
    }

    ManageNotesScreen(navController = navController, noteViewModel = mockViewModel)
}
