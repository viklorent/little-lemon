package com.example.littlelemon

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Profile(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString("firstName", "") ?: ""
    val lastName = sharedPreferences.getString("lastName", "") ?: ""
    val email = sharedPreferences.getString("email", "") ?: ""

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
        )
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

        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 2.dp)
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    color = Color(0xFFEFEFEF),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = firstName,
                style = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Text(
            text = "Last Name",
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp, bottom = 5.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 2.dp)
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    color = Color(0xFFEFEFEF),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = lastName,
                style = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Text(
            text = "Email",
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp, bottom = 5.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 2.dp)
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    color = Color(0xFFEFEFEF),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = email,
                style = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Button(
            onClick = {
                sharedPreferences.edit().clear().apply()
                navController.navigate(Onboarding.route) {
                    popUpTo(Home.route) { inclusive = true }
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
                text = "Log out",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )

        }

    }

}



