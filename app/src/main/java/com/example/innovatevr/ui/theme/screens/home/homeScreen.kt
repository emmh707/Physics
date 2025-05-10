package com.example.innovatevr.ui.theme.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.innovatevr.navigation.ROUTE_ADD_NOTE
import com.example.innovatevr.navigation.ROUTE_PHYSICS

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
    ) {
        PhysicsCard(onOpenPhysics = {
            navController.navigate(ROUTE_PHYSICS)
        })

        NotesCard(onOpenNotes = {
            navController.navigate(ROUTE_ADD_NOTE)
        })
    }
}

@Composable
fun PhysicsCard(onOpenPhysics: () -> Unit) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Physics Lab",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onOpenPhysics,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Explore VR")
            }
        }
    }
}

@Composable
fun NotesCard(onOpenNotes: () -> Unit) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Notes",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onOpenNotes,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Manage Notes")
            }
        }
    }
}