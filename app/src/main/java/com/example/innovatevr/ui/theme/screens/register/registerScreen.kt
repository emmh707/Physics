package com.example.innovatevr.ui.theme.screens.register

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.innovatevr.data.AuthViewModel
import com.example.innovatevr.R
import com.example.innovatevr.navigation.ROUTE_LOGIN
import com.example.innovatevr.navigation.ROUTE_REGISTER

@Composable
fun RegisterScreen(navController: NavController) {

    val authViewModel: AuthViewModel = viewModel()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var fullname by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sign In",
            fontSize = 40.sp,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
            color = Color.Magenta,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Imgur Logo with Fallback
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data("https://i.imgur.com/yourImageId.png") // Replace with your Imgur link
                .crossfade(true)
                .error(R.drawable.baseline_person_24)
                .placeholder(R.drawable.baseline_person_24)
                .build(),
            contentDescription = "Logo",
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .height(80.dp)
        )

        OutlinedTextField(
            value = fullname,
            onValueChange = { fullname = it },
            label = { Text("Full Name") },
            placeholder = { Text("Please enter the full name") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Full name") },
            shape = RoundedCornerShape(8.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            placeholder = { Text("Please enter the email") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") },
            shape = RoundedCornerShape(8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("Please enter the password") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") },
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = confirmpassword,
            onValueChange = { confirmpassword = it },
            label = { Text("Confirm Password") },
            placeholder = { Text("Please confirm password") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") },
            shape = RoundedCornerShape(8.dp)
        )

        Button(
            onClick = {
                authViewModel.signup(
                    fullname,
                    email,
                    password,
                    confirmpassword,
                    navController,
                    context
                )
            },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Magenta)
        ) {
            Text("Sign In")
        }

        Text(
            text = buildAnnotatedString { append("Already have an account? Sign Up") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)
                .clickable {
                    navController.navigate(ROUTE_LOGIN)
                },
            color = Color.Blue
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(rememberNavController())
}
