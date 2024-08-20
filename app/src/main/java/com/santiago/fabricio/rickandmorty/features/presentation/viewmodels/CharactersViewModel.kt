package com.santiago.fabricio.rickandmorty.features.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.santiago.fabricio.rickandmorty.features.domain.usecase.CharactersUseCase
import com.santiago.fabricio.rickandmorty.features.presentation.state.CharactersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase
) : ViewModel() {

    var uiState by mutableStateOf(CharactersState())
        private set

    init {
        getCharacters()
    }

     private fun getCharacters() {
         try {
             val characters = charactersUseCase.invoke()
                 .cachedIn(viewModelScope)
             uiState = uiState.copy(characters = characters)
         } catch (e: Exception){
             uiState = uiState.copy(characters = flowOf())
         }
    }

}