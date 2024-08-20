package com.santiago.fabricio.rickandmorty.features.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.santiago.fabricio.rickandmorty.features.domain.usecase.LocationsUseCase
import com.santiago.fabricio.rickandmorty.features.presentation.state.LocationsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val locationsUseCase: LocationsUseCase
) : ViewModel() {

    var uiState by mutableStateOf(LocationsState())
        private set

    init {
        getLocations()
    }

     private fun getLocations() {
         try {
             val locations = locationsUseCase.invoke()
                 .cachedIn(viewModelScope)
             uiState = uiState.copy(locations = locations)
         } catch (e: Exception){
             uiState = uiState.copy(locations = flowOf())
         }
    }

}