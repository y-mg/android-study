package com.ymg.architecture.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ymg.architecture.feature.photo.photoNavGraph
import com.ymg.architecture.ui.navigator.photo.PhotoNavigator

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = PhotoNavigator.Root.route
    ) {
        photoNavGraph(
            navController = navController
        )
    }
}
