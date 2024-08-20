package com.santiago.fabricio.rickandmorty.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavigationGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = charactersScreenRoute,
    ) {
        charactersScreen(navHostController = navHostController)
        locationsScreen(navHostController = navHostController)
    }
}