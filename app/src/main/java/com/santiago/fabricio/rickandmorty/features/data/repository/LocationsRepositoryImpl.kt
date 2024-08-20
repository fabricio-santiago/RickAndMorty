package com.santiago.fabricio.rickandmorty.features.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.santiago.fabricio.rickandmorty.core.data.remote.model.Location
import com.santiago.fabricio.rickandmorty.features.domain.repository.LocationsRepository
import com.santiago.fabricio.rickandmorty.features.domain.source.LocationsRemoteDataSource
import kotlinx.coroutines.flow.Flow

class LocationsRepositoryImpl(
    private val remoteDataSource: LocationsRemoteDataSource
) : LocationsRepository {
    override fun getLocations(pagingConfig: PagingConfig): Flow<PagingData<Location>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getLocationsPageSource()
            }
        ).flow
    }
}