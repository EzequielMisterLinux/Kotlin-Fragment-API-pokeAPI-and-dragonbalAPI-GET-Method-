package com.example.fragments.api

import com.example.fragments.model.PokemonResponse
import retrofit2.http.GET

interface PokeApiService {
    @GET("pokemon?limit=100")
    suspend fun getPokemon(): PokemonResponse
}