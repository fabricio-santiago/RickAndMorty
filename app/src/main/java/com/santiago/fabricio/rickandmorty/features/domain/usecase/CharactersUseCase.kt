package com.santiago.fabricio.rickandmorty.features.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.santiago.fabricio.rickandmorty.features.domain.repository.CharactersRepository
import com.santiago.fabricio.rickandmorty.core.data.remote.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface CharactersUseCase {
    operator fun invoke(): Flow<PagingData<Character>>
}

class CharactersUseCaseImpl @Inject constructor(
    private val repository: CharactersRepository
) : CharactersUseCase {
    override fun invoke(): Flow<PagingData<Character>> {
        return repository.getCharacters(
            pagingConfig = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            )
        ).flowOn(Dispatchers.IO)
    }
}