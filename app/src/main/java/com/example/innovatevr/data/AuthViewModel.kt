package com.example.innovatevr.data

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.innovatevr.models.UserModel
import com.example.innovatevr.navigation.ROUTE_HOME
import com.example.innovatevr.navigation.ROUTE_LOGIN
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow

class AuthViewModel : ViewModel() {

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _isLoading = MutableStateFlow(false)
    private val _errorMessage = MutableStateFlow<String?>(null)

    fun signup(
        fullname: String,
        email: String,
        password: String,
        confirmpassword: String,
        navController: NavController,
        context: Context
    ) {
        if (fullname.isBlank() || email.isBlank() || password.isBlank() || confirmpassword.isBlank()) {
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_LONG).show()
            return
        }

        if (password != confirmpassword) {
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
            return
        }

        _isLoading.value = true

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val userId = mAuth.currentUser?.uid ?: return@addOnSuccessListener

                val userData = UserModel(
                    fullname = fullname,
                    email = email,
                    password = password,
                    confirmpassword = confirmpassword,
                    userId = userId
                )

                saveUserToDatabase(userId, userData, navController, context)
                _isLoading.value = false
            }
            .addOnFailureListener { exception ->
                _isLoading.value = false
                _errorMessage.value = exception.message
                Toast.makeText(context, "Sign Up Failed: ${exception.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun saveUserToDatabase(
        userId: String,
        userData: UserModel,
        navController: NavController,
        context: Context
    ) {
        val userRef = FirebaseDatabase.getInstance().getReference("Users/$userId")
        userRef.setValue(userData)
            .addOnSuccessListener {
                Toast.makeText(context, "User Registered Successfully", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_LOGIN) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
            }
            .addOnFailureListener { exception ->
                _errorMessage.value = exception.message
                Toast.makeText(context, "Database Error: ${exception.message}", Toast.LENGTH_LONG).show()
            }
    }

    fun login(
        email: String,
        password: String,
        navController: NavController,
        context: Context
    ) {
        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(context, "Email and Password are required", Toast.LENGTH_LONG).show()
            return
        }

        _isLoading.value = true

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                _isLoading.value = false
                Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_HOME)
            }
            .addOnFailureListener { exception ->
                _isLoading.value = false
                _errorMessage.value = exception.message
                Toast.makeText(context, "Login Failed: ${exception.message}", Toast.LENGTH_LONG).show()
            }
    }

    fun logout(
        navController: NavController,
        context: Context
    ) {
        mAuth.signOut()
        Toast.makeText(context, "User Logged Out", Toast.LENGTH_LONG).show()
        navController.navigate(ROUTE_LOGIN)
    }
}
