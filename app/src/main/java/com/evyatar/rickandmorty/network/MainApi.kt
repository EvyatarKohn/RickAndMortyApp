package com.evyatar.rickandmorty.network

import com.evyatar.rickandmorty.model.characterlistmodel.CharactersList
import com.evyatar.rickandmorty.model.charactermodel.Characters
import com.evyatar.rickandmorty.model.episodesmodel.Episodes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApi {
    @GET("character")
    suspend fun getCharacterNamesAndImages(): Response<Characters>

    @GET("character/{ids}")
    suspend fun getCharacterNamesAndImagesById(@Path("ids") ids: String): Response<CharactersList>

    @GET("episode/{id}")
    suspend fun getEpisodes(@Path("id") id: Int): Response<Episodes>

}