package com.santiago.fabricio.rickandmorty.features.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.santiago.fabricio.rickandmorty.core.data.remote.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {
    fun getLocations(pagingConfig: PagingConfig): Flow<PagingData<Location>>
}