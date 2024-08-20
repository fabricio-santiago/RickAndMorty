package com.santiago.fabricio.rickandmorty.features.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.santiago.fabricio.rickandmorty.core.data.remote.model.Character
import com.santiago.fabricio.rickandmorty.features.domain.repository.CharactersRepository
import com.santiago.fabricio.rickandmorty.features.domain.source.CharactersRemoteDataSource
import kotlinx.coroutines.flow.Flow

class CharactersRepositoryImpl(
    private val remoteDataSource: CharactersRemoteDataSource
) : CharactersRepository {
    override fun getCharacters(pagingConfig: PagingConfig): Flow<PagingData<Character>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getCharactersPageSource()
            }
        ).flow
    }
}


