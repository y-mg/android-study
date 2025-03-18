package com.ymg.architecture.ui.design.component.composable

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ymg.architecture.ui.design.color.customColorScheme
import kotlinx.coroutines.delay

fun NavGraphBuilder.transitionComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content:
    @Composable()
    (AnimatedContentScope.(NavBackStackEntry) -> Unit)
) {
    composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        content = { navBackStackEntry ->
            if (navBackStackEntry.destination.route == ("onboard/landing")) {
                return@composable content(navBackStackEntry)
            }

            var slideCompleted by rememberSaveable(navBackStackEntry.id) {
                mutableStateOf(false)
            }

            LaunchedEffect(navBackStackEntry) {
                if (!slideCompleted) {
                    delay(
                        timeMillis = 300
                    )
                    slideCompleted = true
                }
            }

            Crossfade(
                modifier = Modifier.fillMaxSize()
                    .background(
                        color = MaterialTheme.customColorScheme.white
                    ),
                targetState = slideCompleted,
                label = "Crossfade"
            ) { isCompleted ->
                if (isCompleted) {
                    content(navBackStackEntry)
                } else {
                    BackHandler {}
                }
            }
        }
    )
}
