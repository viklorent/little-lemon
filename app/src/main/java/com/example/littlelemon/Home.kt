package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import java.util.Locale


@Composable
fun Home(navController: NavHostController, database: AppDatabase) {
    val menuItemsDatabase by database.menuItemDao().getAll().observeAsState(emptyList())
    var menuItems by remember { mutableStateOf(emptyList<MenuItemRoom>()) }
    var selectedCategory by remember { mutableStateOf("") }
    var searchPhrase by remember { mutableStateOf("") }

    LaunchedEffect(menuItemsDatabase) {
        menuItems = menuItemsDatabase
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Spacer(modifier = Modifier.weight(0.5f))
            Spacer(modifier = Modifier.width(50.dp))
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    .height(50.dp)
                    .width(200.dp)
            )
            Spacer(modifier = Modifier.weight(0.5f))
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .padding(end = 15.dp)
                    .size(50.dp)
                    .clip(CircleShape)
                    .clickable { navController.navigate(Profile.route) },
            )
        }

        // Hero Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF4C5E57))
                .padding(16.dp),
        ) {
            Text(
                text = "Little Lemon",
                color = Color(0xFFEECF00),
                fontSize = 45.sp,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.displayLarge
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                Column(modifier = Modifier.weight(0.6f)) {
                    Text(
                        text = "Chicago",
                        color = Color.White,
                        fontSize = 30.sp,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                        color = Color.White,
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.hero),
                    contentDescription = "Sandwich",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                )
            }

            OutlinedTextField(
                placeholder = { Text("Enter Search Phrase") },
                value = searchPhrase,
                onValueChange = {
                    searchPhrase = it
                    menuItems = if (it.isNotEmpty()) {
                        menuItemsDatabase.filter { item ->
                            item.title.contains(
                                it,
                                ignoreCase = true
                            )
                        }
                    } else {
                        menuItemsDatabase
                    }
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFEAEAEA)),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black,
                    cursorColor = Color.Black,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    unfocusedContainerColor = Color(0xFFEAEAEA),
                    focusedContainerColor = Color(0xFFEAEAEA)
                ),
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            )
        }

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = "ORDER FOR DELIVERY!",
                modifier = Modifier.padding(top = 30.dp),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                style = MaterialTheme.typography.bodyLarge
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp)
            ) {
                listOf("starters", "mains", "desserts", "drinks").forEach { category ->
                    Button(
                        onClick = {
                            selectedCategory = if (selectedCategory == category) "" else category
                            menuItems = if (selectedCategory.isEmpty()) {
                                menuItemsDatabase
                            } else {
                                menuItemsDatabase.filter { it.category.contains(selectedCategory) }
                            }
                        },
                        modifier = Modifier
                            .height(40.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFEDEFEE)
                        ),
                        shape = RoundedCornerShape(15.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = category.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.ROOT
                                ) else it.toString()
                            },
                            fontWeight = FontWeight.Bold,
                            color = if (selectedCategory == category) Color.White else Color(0xFF4C5E57),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }

        MenuItems(menuItemsList = menuItems)
    }
}

@Composable
fun MenuItems(menuItemsList: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        items(items = menuItemsList, itemContent = { menuItem -> MenuItem(menuItem) })
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(menuItem: MenuItemRoom) {
    HorizontalDivider(
        thickness = 2.dp,
        color = Color(0xFFDDDDDD),
        modifier = Modifier.padding(vertical = 15.dp)
    )
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        Text(text = menuItem.title, fontWeight = FontWeight.Bold, fontSize = 15.sp,
            style = MaterialTheme.typography.bodyLarge)
        Row(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = menuItem.description, fontSize = 13.sp, color = Color.Gray,
                    style = MaterialTheme.typography.bodyLarge)
                Text(
                    text = "$${menuItem.price}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            GlideImage(
                model = menuItem.image,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 15.dp)
                    .size(100.dp),
                contentScale = ContentScale.Crop,
            )
        }
    }
}



