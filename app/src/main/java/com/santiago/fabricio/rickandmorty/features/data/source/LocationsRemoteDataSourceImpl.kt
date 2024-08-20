package com.santiago.fabricio.rickandmorty.features.data.source

import com.santiago.fabricio.rickandmorty.core.data.remote.response.locations.LocationsResponse
import com.santiago.fabricio.rickandmorty.core.data.remote.service.util.SafeApiCaller
import com.santiago.fabricio.rickandmorty.core.data.remote.paging.LocationsPageSource
import com.santiago.fabricio.rickandmorty.core.data.remote.service.LocationsService
import com.santiago.fabricio.rickandmorty.features.domain.source.LocationsRemoteDataSource
import javax.inject.Inject

class LocationsRemoteDataSourceImpl @Inject constructor(
    private val service: LocationsService,
    private val safeApiCaller: SafeApiCaller
) : LocationsRemoteDataSource {
    override fun getLocationsPageSource(): LocationsPageSource {
        return LocationsPageSource(this, safeApiCaller)
    }

    override suspend fun getLocations(page: Int): LocationsResponse {
        return service.getLocations(page)
    }
}