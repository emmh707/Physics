package com.example.innovatevr.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.innovatevr.data.NoteViewModel
import com.example.innovatevr.ui.theme.screens.*
import com.example.innovatevr.ui.theme.screens.home.HomeScreen
import com.example.innovatevr.ui.theme.screens.login.LoginScreen
import com.example.innovatevr.ui.theme.screens.notes.*
import com.example.innovatevr.ui.theme.screens.physics.ElectromagneticInductionScreen
import com.example.innovatevr.ui.theme.screens.profile.ProfileScreen
import com.example.innovatevr.ui.theme.screens.register.RegisterScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(ROUTE_SPLASH) {
            SplashScreen {
                navController.navigate(ROUTE_REGISTER) {
                    popUpTo(ROUTE_SPLASH) { inclusive = true }
                }
            }
        }
        composable(ROUTE_REGISTER) { RegisterScreen(navController) }
        composable(ROUTE_LOGIN) { LoginScreen(navController) }
        composable(ROUTE_HOME) { HomeScreen(navController) }
        composable(ROUTE_PHYSICS) { ElectromagneticInductionScreen(navController) }
        composable(ROUTE_ADD_NOTE) {
            AddNoteScreen(navController, noteViewModel = NoteViewModel())
        }
        composable("$ROUTE_UPDATE_NOTE/{noteId}/{oldTitle}/{oldContent}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId") ?: ""
            val oldTitle = backStackEntry.arguments?.getString("oldTitle") ?: ""
            val oldContent = backStackEntry.arguments?.getString("oldContent") ?: ""
            UpdateNoteScreen(
                navController = navController,
                noteId = noteId,
                oldTitle = oldTitle,
                oldContent = oldContent,
                noteViewModel = NoteViewModel()
            )
        }
        composable("$ROUTE_DELETE_NOTE/{noteId}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId") ?: ""
            DeleteNoteScreen(
                context = LocalContext.current,
                noteId = noteId,
                navController = navController,
                noteViewModel = NoteViewModel(),
                onDismiss = { navController.popBackStack() }
            )
        }
        composable(ROUTE_MANAGE_NOTE) {
            ManageNotesScreen(navController = navController, noteViewModel = NoteViewModel())
        }
        composable(ROUTE_PROFILE) {
            val dummyUser = null
            ProfileScreen(navController, dummyUser)
        }
    }
}







