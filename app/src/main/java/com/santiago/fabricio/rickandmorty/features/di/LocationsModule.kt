package com.santiago.fabricio.rickandmorty.features.di

import com.santiago.fabricio.rickandmorty.core.data.remote.service.LocationsService
import com.santiago.fabricio.rickandmorty.core.data.remote.service.util.SafeApiCaller
import com.santiago.fabricio.rickandmorty.features.data.repository.LocationsRepositoryImpl
import com.santiago.fabricio.rickandmorty.features.data.source.LocationsRemoteDataSourceImpl
import com.santiago.fabricio.rickandmorty.features.domain.repository.LocationsRepository
import com.santiago.fabricio.rickandmorty.features.domain.source.LocationsRemoteDataSource
import com.santiago.fabricio.rickandmorty.features.domain.usecase.LocationsUseCase
import com.santiago.fabricio.rickandmorty.features.domain.usecase.LocationsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationsModule {

    @Provides
    @Singleton
    fun provideLocationsRemoteDataSource(
        service: LocationsService,
        safeApiCaller: SafeApiCaller
    ): LocationsRemoteDataSource {
        return LocationsRemoteDataSourceImpl(service = service, safeApiCaller = safeApiCaller)
    }

    @Provides
    @Singleton
    fun provideLocationsRepository(remoteDataSource: LocationsRemoteDataSource): LocationsRepository {
        return LocationsRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideLocationsUseCase(repository: LocationsRepository): LocationsUseCase {
        return LocationsUseCaseImpl(repository = repository)
    }
}