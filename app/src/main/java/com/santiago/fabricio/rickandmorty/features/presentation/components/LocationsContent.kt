package com.santiago.fabricio.rickandmorty.features.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.santiago.fabricio.rickandmorty.R
import com.santiago.fabricio.rickandmorty.core.components.ErrorView
import com.santiago.fabricio.rickandmorty.core.components.LoadingView
import com.santiago.fabricio.rickandmorty.core.data.remote.model.Location

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LocationsContent(
    modifier: Modifier = Modifier,
    locations: LazyPagingItems<Location>,
    paddingValues: PaddingValues,
) {

    val context = LocalContext.current

    Surface(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = paddingValues,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .clearAndSetSemantics {
                    contentDescription =
                        context.getString(R.string.locations_content_description_lazy_vertical_grid)
                }
        ) {
            items(locations.itemCount) { index ->
                val location = locations[index]
                location?.let { loc ->
                    LocationItem(
                        location = loc,
                    )
                }
            }

            locations.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item(
                            span = { GridItemSpan(maxLineSpan) }
                        ) {
                            LoadingView()
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item(
                            span = { GridItemSpan(maxLineSpan) }
                        ) {
                            LoadingView()
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        item(
                            span = { GridItemSpan(maxLineSpan) }
                        ) {
                            ErrorView(
                                message = stringResource(id = R.string.locations_screen_error_message),
                                retry = {
                                    retry()
                                })
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        item(
                            span = { GridItemSpan(maxLineSpan) }
                        ) {
                            ErrorView(
                                message = stringResource(id = R.string.locations_screen_error_message),
                                retry = {
                                    retry()
                                })
                        }
                    }
                }
            }
        }
    }
}