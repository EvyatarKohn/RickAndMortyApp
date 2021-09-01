package com.evyatar.rickandmorty.network

import com.evyatar.rickandmorty.model.charactermodel.Characters
import retrofit2.Response
import retrofit2.http.GET

interface MainApi {
    @GET("character")
    suspend fun getCharacterNamesAndImages(): Response<Characters>

}