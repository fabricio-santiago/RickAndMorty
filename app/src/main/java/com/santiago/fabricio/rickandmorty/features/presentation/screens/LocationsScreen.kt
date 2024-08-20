package com.santiago.fabricio.rickandmorty.features.presentation.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.santiago.fabricio.rickandmorty.R
import com.santiago.fabricio.rickandmorty.core.components.CustomAppBar
import com.santiago.fabricio.rickandmorty.features.presentation.components.LocationsContent
import com.santiago.fabricio.rickandmorty.features.presentation.state.LocationsState

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LocationsScreen(
    uiState: LocationsState,
    navigateToCharacters: () -> Unit,
) {
    val locations = uiState.locations.collectAsLazyPagingItems()

    Scaffold(topBar = {
        CustomAppBar(
            title = stringResource(id = R.string.locations_screen_title_app_bar),
            showAppBarButton = true,
            onBackButtonClick = { navigateToCharacters() }
        )
    },
    content = { paddingValues ->
        LocationsContent(
            locations = locations,
            paddingValues = paddingValues,
        )
    })
}