package com.cgcreativesolutions.sampledadosabertosdacamera.ui.app

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.screens.eventdetails.EventDetailsScreen
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.screens.home.HomeNavigation
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.screens.home.HomeScreen
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.screens.home.HomeViewModel

private val viewModel = HomeViewModel()

@Composable
fun App() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) { innerPadding ->
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Home,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 1000 },
                    animationSpec = tween(durationMillis = 3000)
                ) + fadeIn(
                    animationSpec = tween(durationMillis = 3000)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(durationMillis = 3000)
                ) + fadeOut(
                    animationSpec = tween(durationMillis = 3000)
                )
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn()
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut()
            }
        ) {
            composable<Home> {
                val state by viewModel.state.collectAsState()
                HomeScreen(
                    modifier = Modifier
                        .padding(innerPadding),
                    state = state,
                    onEvent = { event -> viewModel.onEvent(event) },
                    onNavigate = { destination ->
                        when(destination) {
                            is HomeNavigation.ToEventDetails -> {
                                navController.navigate(
                                    EventDetails(
                                        id = destination.eventId,
                                        eventType = destination.eventType
                                    )
                                )
                            }
                        }
                    }
                )
            }

            composable<EventDetails> { backStackEntry ->
                val args = backStackEntry.toRoute<EventDetails>()
                EventDetailsScreen(
                    modifier = Modifier.background(Color.Blue),
                    eventId = args.id,
                    eventType = args.eventType
                )
            }
        }
    }
}