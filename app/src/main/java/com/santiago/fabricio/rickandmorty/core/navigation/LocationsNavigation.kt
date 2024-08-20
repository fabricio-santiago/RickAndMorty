package com.santiago.fabricio.rickandmorty.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.santiago.fabricio.rickandmorty.features.presentation.screens.LocationsScreen
import com.santiago.fabricio.rickandmorty.features.presentation.state.LocationsState
import com.santiago.fabricio.rickandmorty.features.presentation.viewmodels.LocationsViewModel

const val locationsScreenRoute = "locationsScreenRoute"

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.locationsScreen(
    navHostController: NavController
) {
    composable(route = locationsScreenRoute) {

        val viewModel: LocationsViewModel = hiltViewModel()
        val uiState: LocationsState = viewModel.uiState

        LocationsScreen(
            uiState = uiState,
            navigateToCharacters = { navHostController.navigateToCharactersScreen() },
        )
    }
}

fun NavController.navigateToLocationsScreen() {
    navigate(route = locationsScreenRoute)
}