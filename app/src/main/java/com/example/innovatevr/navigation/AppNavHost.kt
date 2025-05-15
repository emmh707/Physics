package com.example.innovatevr.navigation
//
//import android.net.Uri
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.platform.LocalContext
//import androidx.navigation.NavType
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.navArgument
//import com.example.innovatevr.data.NoteViewModel
//import com.example.innovatevr.ui.theme.screens.*
//import com.example.innovatevr.ui.theme.screens.home.HomeScreen
//import com.example.innovatevr.ui.theme.screens.login.LoginScreen
//import com.example.innovatevr.ui.theme.screens.notes.*
//import com.example.innovatevr.ui.theme.screens.physics.ElectromagneticInductionScreen
//import com.example.innovatevr.ui.theme.screens.profile.ProfileScreen
//import com.example.innovatevr.ui.theme.screens.register.RegisterScreen
//
//@Composable
//fun AppNavHost(
//    navController: androidx.navigation.NavHostController = rememberNavController(),
//    startDestination: String = ROUTE_SPLASH,
//    noteViewModel: NoteViewModel = NoteViewModel()
//) {
//    val context = LocalContext.current
//
//    NavHost(navController = navController, startDestination = startDestination) {
//        composable(ROUTE_SPLASH) {
//            SplashScreen {
//                navController.navigate(ROUTE_REGISTER) {
//                    popUpTo(ROUTE_SPLASH) { inclusive = true }
//                }
//            }
//        }
//
//        composable(ROUTE_REGISTER) { RegisterScreen(navController) }
//        composable(ROUTE_LOGIN) { LoginScreen(navController) }
//        composable(ROUTE_HOME) { HomeScreen(navController) }
//
//        composable(ROUTE_PHYSICS) {
//            ElectromagneticInductionScreen(navController)
//        }
//
//        composable(ROUTE_ADD_NOTE) {
//            AddNoteScreen(navController, noteViewModel = noteViewModel)
//        }
//
//        composable(
//            route = "$ROUTE_UPDATE_NOTE/{noteId}/{encodedTitle}/{encodedContent}",
//            arguments = listOf(
//                navArgument("noteId") { type = NavType.StringType },
//                navArgument("encodedTitle") { type = NavType.StringType },
//                navArgument("encodedContent") { type = NavType.StringType },
//            )
//        ) { backStackEntry ->
//            val noteId = backStackEntry.arguments?.getString("noteId") ?: ""
//            val encodedTitle = backStackEntry.arguments?.getString("encodedTitle") ?: ""
//            val encodedContent = backStackEntry.arguments?.getString("encodedContent") ?: ""
//
//            val oldTitle = Uri.decode(encodedTitle)
//            val oldContent = Uri.decode(encodedContent)
//
////            UpdateNoteScreen(
////                navController = navController,
////                noteId = noteId,
////                oldTitle = oldTitle,
////                oldContent = oldContent,
////                noteViewModel = noteViewModel
////            )
////        }
//
//        composable(ROUTE_MANAGE_NOTE) {
//            ManageNotesScreen(navController = navController, noteViewModel = noteViewModel)
//        }
//
//        composable(ROUTE_PROFILE) {
//            ProfileScreen(navController, dummyUser = null)
//        }
//    }
//}