package com.santiago.fabricio.rickandmorty.features.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.santiago.fabricio.rickandmorty.core.data.remote.model.Location
import com.santiago.fabricio.rickandmorty.features.domain.repository.LocationsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface LocationsUseCase {
    operator fun invoke(): Flow<PagingData<Location>>
}

class LocationsUseCaseImpl @Inject constructor(
    private val repository: LocationsRepository
) : LocationsUseCase {
    override fun invoke(): Flow<PagingData<Location>> {
        return repository.getLocations(
            pagingConfig = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            )
        ).flowOn(Dispatchers.IO)
    }
}