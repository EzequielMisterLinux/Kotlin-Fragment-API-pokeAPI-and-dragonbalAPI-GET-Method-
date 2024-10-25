package com.example.fragments.api

import com.example.fragments.model.DragonBallCharacter
import retrofit2.http.GET


data class DragonBallResponse(
    val items: List<DragonBallCharacter>
)

interface DragonBallApiService {
    @GET("api/characters")
    suspend fun getCharacters(): DragonBallResponse
}
