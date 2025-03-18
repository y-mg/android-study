package com.ymg.architecture.ui.navigator.photo

import androidx.navigation.NavHostController

sealed class PhotoNavigator(
    val route: String
) {
    companion object {
        private const val ROOT = "photo"

		fun NavHostController.photoNavigateToDetail(
            id: String
        ) = navigate(
            Detail.passId(
                id = id
            )
        ) {
			launchSingleTop = true
		}
    }

    data object Root : PhotoNavigator(
        route = ROOT
    )

    data object Grid : PhotoNavigator(
        route = "$ROOT/grid"
    )

    data object Detail : PhotoNavigator(
        route = "$ROOT/detail/{id}"
    ) {
        fun passId(
            id: String
        ) = "$ROOT/detail/$id"
    }
}
