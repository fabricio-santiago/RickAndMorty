package com.santiago.fabricio.rickandmorty.features.di

import com.santiago.fabricio.rickandmorty.core.data.remote.service.CharactersService
import com.santiago.fabricio.rickandmorty.core.data.remote.service.util.SafeApiCaller
import com.santiago.fabricio.rickandmorty.features.data.repository.CharactersRepositoryImpl
import com.santiago.fabricio.rickandmorty.features.data.source.CharactersRemoteDataSourceImpl
import com.santiago.fabricio.rickandmorty.features.domain.repository.CharactersRepository
import com.santiago.fabricio.rickandmorty.features.domain.source.CharactersRemoteDataSource
import com.santiago.fabricio.rickandmorty.features.domain.usecase.CharactersUseCase
import com.santiago.fabricio.rickandmorty.features.domain.usecase.CharactersUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharactersModule {

    @Provides
    @Singleton
    fun provideCharactersRemoteDataSource(
        service: CharactersService,
        safeApiCaller: SafeApiCaller
    ): CharactersRemoteDataSource {
        return CharactersRemoteDataSourceImpl(service = service, safeApiCaller = safeApiCaller)
    }

    @Provides
    @Singleton
    fun provideCharactersRepository(remoteDataSource: CharactersRemoteDataSource): CharactersRepository {
        return CharactersRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideCharactersUseCase(repository: CharactersRepository): CharactersUseCase {
        return CharactersUseCaseImpl(repository = repository)
    }
}