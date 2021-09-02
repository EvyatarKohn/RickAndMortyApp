package com.evyatar.rickandmorty.repository

import com.evyatar.rickandmorty.network.MainApi
import javax.inject.Inject

class RickAndMortyRepository @Inject constructor(private val mainApi: MainApi) {

    suspend fun getCharacterNamesAndImages() = mainApi.getCharacterNamesAndImages()

    suspend fun getCharacterNamesAndImagesById(ids: String) = mainApi.getCharacterNamesAndImagesById(ids)

    suspend fun getEpisodes(id: Int) = mainApi.getEpisodes(id)
}