package com.example.innovatevr.ui.theme.screens.notes

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.innovatevr.data.NoteViewModel

@Composable
fun UpdateNoteScreen(
    navController: NavController,
    noteId: String,
    oldTitle: String,
    oldContent: String,
    noteViewModel: NoteViewModel
) {
    val context = LocalContext.current
    var title by remember { mutableStateOf(oldTitle) }
    var content by remember { mutableStateOf(oldContent) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Update Note", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Content") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (title.isNotBlank() && content.isNotBlank()) {
                    noteViewModel.updateNote(context, navController, title, content, noteId)
                    navController.popBackStack()
                } else {
                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Update")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UpdateNoteScreenPreview() {
    val navController = rememberNavController()
    val fakeNoteViewModel = NoteViewModel()

    UpdateNoteScreen(
        navController = navController,
        noteId = "previewNoteId",
        oldTitle = "Magnetic Fields",
        oldContent = "This note explains how electromagnetic induction works...",
        noteViewModel = fakeNoteViewModel
    )
}
