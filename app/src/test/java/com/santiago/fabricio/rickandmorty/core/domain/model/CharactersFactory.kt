package com.santiago.fabricio.rickandmorty.core.domain.model

import com.santiago.fabricio.rickandmorty.core.data.remote.model.Character
import com.santiago.fabricio.rickandmorty.core.data.remote.model.Info
import com.santiago.fabricio.rickandmorty.core.data.remote.model.OriginLocation
import com.santiago.fabricio.rickandmorty.core.data.remote.response.characters.CharactersResponse

class CharactersFactory {

    companion object {
        fun create() = run {
            CharactersResponse(
                info = Info(
                    count = 10,
                    pages = 1,
                    next = "",
                    prev = ""
                ),
                results = listOf(
                    Character(
                        id = 361,
                        name = "Toxic Rick",
                        status = "Dead",
                        species = "Humanoid",
                        type = "Rick's Toxic Side",
                        gender = "Male",
                        origin = OriginLocation(
                            name = "Alien Spa",
                            url = "https://rickandmortyapi.com/api/location/64"
                        ),
                        location = OriginLocation(
                            name = "Earth",
                            url = "https://rickandmortyapi.com/api/location/20"
                        ),
                        image = "https://rickandmortyapi.com/api/character/avatar/361.jpeg",
                        episode = listOf(
                            "https://rickandmortyapi.com/api/episode/27"
                        ),
                        url = "https://rickandmortyapi.com/api/character/361",
                        created = "2018-01-10T18:20:41.703Z"

                    )
                )
            )
        }
    }
}