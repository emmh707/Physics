package com.example.innovatevr.ui.theme.screens.physics

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ElectromagneticInductionScreen(navController: NavController) {
    val context = LocalContext.current
    var notes by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Electromagnetic Induction", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Summary:\nElectromagnetic induction is the process of generating electric current using a magnetic field. It is the working principle behind transformers and generators.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(
                    Uri.parse("file:///android_asset/electromagnetic_induction.glb"),
                    "model/gltf-binary"
                )
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "No app found to view 3D model", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("View 3D Model")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Your Notes") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}