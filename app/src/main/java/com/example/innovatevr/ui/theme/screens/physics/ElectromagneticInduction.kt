package com.example.innovatevr.ui.theme.screens.physics

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.innovatevr.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElectromagneticInductionScreen(navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Electromagnetic Induction") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Summary",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Electromagnetic induction is the production of voltage across a conductor in a changing magnetic field. It is fundamental in many electrical devices such as transformers and electric generators.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "360° Virtual Lab View",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
//            Image(
//                painter = painterResource(id = R.drawable.induction_image), // <-- Make sure this drawable exists
//                contentDescription = "360 Lab",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp)
//                    .clickable {
//                        val intent = Intent(
//                            Intent.ACTION_VIEW,
//                            Uri.parse("https://www.360inductionlab.com")
//                        )
//                        context.startActivity(intent)
//                    },
//                contentScale = ContentScale.Crop
//            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Your Notes",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Button(
                onClick = { navController.navigate("notes_screen") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Open Notes")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ElectromagneticInductionScreenPreview() {
    ElectromagneticInductionScreen(navController = rememberNavController())
}
