package com.santiago.fabricio.rickandmorty.features.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.santiago.fabricio.rickandmorty.R
import com.santiago.fabricio.rickandmorty.core.components.CustomAppBar
import com.santiago.fabricio.rickandmorty.features.presentation.components.CharactersContent
import com.santiago.fabricio.rickandmorty.features.presentation.state.CharactersState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CharactersScreen(
    uiState: CharactersState,
    navHostController: NavController
) {
    val characters = uiState.characters.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            CustomAppBar(
                title = stringResource(id = R.string.characters_screen_title_app_bar),
            )
        },
        content = { paddingValues ->
            CharactersContent(
                pagingCharacters = characters,
                paddingValues = paddingValues,
                navHostController = navHostController
            )
        })
}