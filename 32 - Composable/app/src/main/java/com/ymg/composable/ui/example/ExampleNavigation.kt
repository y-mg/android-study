package com.ymg.composable.ui.example

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ExampleNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("details") { DetailScreen() }
    }
}

@Composable
private fun HomeScreen(navController: NavController) {
    Column {
        Text(text = "홈 화면")
        Button(
            onClick = {
                navController.navigate("details")
            }
        ) {
            Text("상세 화면으로 이동")
        }
    }
}

@Composable
private fun DetailScreen() {
    Text("상세 화면")
}