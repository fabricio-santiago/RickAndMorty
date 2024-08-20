package com.santiago.fabricio.rickandmorty.core.data.remote.response.characters

import com.google.gson.annotations.SerializedName
import com.santiago.fabricio.rickandmorty.core.data.remote.model.Character
import com.santiago.fabricio.rickandmorty.core.data.remote.model.Info

data class CharactersResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Character>,
)