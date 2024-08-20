package com.santiago.fabricio.rickandmorty.features.presentation.state

import androidx.paging.PagingData
import com.santiago.fabricio.rickandmorty.core.data.remote.model.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class LocationsState(
    var locations: Flow<PagingData<Location>> = emptyFlow()
)

