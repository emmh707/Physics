package com.example.innovatevr.ui.theme.screens.physics

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.innovatevr.navigation.ROUTE_MANAGE_NOTE
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import coil.compose.rememberImagePainter
import com.example.innovatevr.R

@Composable
fun ElectromagneticInductionScreen(navController: NavController) {
    val context = LocalContext.current
    var notes by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Electromagnetic Induction",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 360° View Image
//        Image(
//            painter = rememberImagePainter(data = R.drawable.), // Replace with your actual thumbnail or 360 view
//            contentDescription = "360 View",
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp)
//                .clickable {
//                    // Navigate to your 360 viewer screen
//                    navController.navigate("vr_viewer")
//                }
//        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Description",
            fontSize = 20.sp,
            color = Color.White
        )
        Text(
            text = "When the magnet is moved inside the coil, a change in magnetic flux induces a current in the circuit, lighting the bulb. This demonstrates Faraday's Law of Electromagnetic Induction.",
            fontSize = 16.sp,
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Your Notes",
            fontSize = 20.sp,
            color = Color.White
        )

//        OutlinedTextField(
//            value = notes,
//            onValueChange = { notes = it },
//            label = { Text("Write your notes here") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(150.dp),
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                textColor = Color.White,
//                backgroundColor = Color.DarkGray
//            )
//        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Save or handle notes here
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Save Notes")
        }
    }

}