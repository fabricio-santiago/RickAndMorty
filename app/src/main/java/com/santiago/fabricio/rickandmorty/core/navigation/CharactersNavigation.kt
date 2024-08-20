package com.santiago.fabricio.rickandmorty.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.santiago.fabricio.rickandmorty.features.presentation.screens.CharactersScreen
import com.santiago.fabricio.rickandmorty.features.presentation.viewmodels.CharactersViewModel
import com.santiago.fabricio.rickandmorty.features.presentation.state.CharactersState

const val charactersScreenRoute = "charactersScreen"

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.charactersScreen(
    navHostController: NavController
) {
    composable(charactersScreenRoute) {

        val viewModel: CharactersViewModel = hiltViewModel()
        val uiState: CharactersState = viewModel.uiState

        CharactersScreen(
            uiState = uiState,
            navHostController = navHostController
        )

    }
}

fun NavController.navigateToCharactersScreen() {
    navigate(route = charactersScreenRoute)
}