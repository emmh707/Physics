package com.example.innovatevr.ui.theme.screens.notes

import android.content.Context
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

    // Load notes
    LaunchedEffect(Unit) {
        noteViewModel.viewNotes(currentNote, notes, context)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

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

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            TextButton(onClick = {
                                // Navigate to UpdateNoteScreen
                                navController.navigate("update_note/${note.noteId}/${note.title}/${note.content}")
                            }) {
                                Text("Edit")
                            }
                            TextButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ManageNotesScreenPreview() {
    val navController = rememberNavController()

    val mockViewModel = (object : NoteViewModel() {
        override fun viewNotes(
            note: MutableState<NoteModel>,
            notes: SnapshotStateList<NoteModel>,
            context: Context
        ) {
            notes.clear()
            notes.addAll(
                listOf(
                    NoteModel(
                        noteId = "1",
                        title = "Demo Note 1",
                        content = "This is a test note."
                    ),
                    NoteModel(
                        noteId = "2",
                        title = "Demo Note 2",
                        content = "Another example content."
                    )
                )
            )
            return
        }
    }).apply {

        ManageNotesScreen(navController = navController, noteViewModel = this)
    }
}
