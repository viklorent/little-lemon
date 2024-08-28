package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun Onboarding(navController: NavHostController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .padding(20.dp)
                .height(50.dp)
                .fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
                .height(75.dp)
                .background(color = Color(0xFF495E57)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Let's get to know you",
                fontSize = 22.sp,
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Personal information",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(horizontal = 20.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Text(
            text = "First Name",
            modifier = Modifier.padding(horizontal = 20.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(fontSize = 14.sp),
            singleLine = true,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 2.dp)
                .fillMaxWidth()
                .height(50.dp)
        )

        Text(
            text = "Last Name",
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp, bottom = 5.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(fontSize = 14.sp),
            singleLine = true,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 2.dp)
                .fillMaxWidth()
                .height(50.dp)
        )

        Text(
            text = "Email",
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp, bottom = 5.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(fontSize = 14.sp),
            singleLine = true,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 2.dp)
                .fillMaxWidth()
                .height(50.dp)
        )

        Button(
            onClick = {
                if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                    Toast.makeText(
                        context,
                        "Registration unsuccessful. Please enter all data.",
                        Toast.LENGTH_LONG
                    ).show()
                } else {

                    sharedPreferences.edit().apply {
                        putString("firstName", firstName)
                        putString("lastName", lastName)
                        putString("email", email)
                        apply()
                    }
                    Toast.makeText(context, "Registration successful!", Toast.LENGTH_LONG).show()

                    navController.navigate(Home.route) {
                        popUpTo(Onboarding.route) { inclusive = true }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 30.dp, top = 60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF4CE14),
                contentColor = Color(0xFF000000)
            ),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text(
                text = "Register",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )

        }


    }

}


@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    val navController = rememberNavController()
    Onboarding(navController)
}