package com.ymg.architecture.feature.photo

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.ymg.architecture.feature.photo.detail.screen.PhotoDetailScreen
import com.ymg.architecture.feature.photo.grid.screen.PhotoGridScreen
import com.ymg.architecture.ui.design.component.composable.transitionComposable
import com.ymg.architecture.ui.navigator.NavigatorConstant
import com.ymg.architecture.ui.navigator.photo.PhotoNavigator

fun NavGraphBuilder.photoNavGraph(
    navController: NavHostController
) {
    navigation(
        route = PhotoNavigator.Root.route,
        startDestination = PhotoNavigator.Grid.route
    ) {
        transitionComposable(
            route = PhotoNavigator.Grid.route
        ) {
            PhotoGridScreen(
                navController = navController
            )
        }

        transitionComposable(
            route = PhotoNavigator.Detail.route,
            arguments = listOf(navArgument(NavigatorConstant.KEY_ID) {
                type = NavType.StringType
            })
        ) {
            PhotoDetailScreen(
                navController = navController
            )
        }
    }
}
