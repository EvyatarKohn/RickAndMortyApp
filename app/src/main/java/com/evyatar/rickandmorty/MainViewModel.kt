package com.evyatar.rickandmorty

import android.app.Application
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.evyatar.rickandmorty.model.characterlistmodel.CharactersList
import com.evyatar.rickandmorty.model.charactermodel.Characters
import com.evyatar.rickandmorty.model.episodesmodel.Episodes
import com.evyatar.rickandmorty.repository.RickAndMortyRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val repository: RickAndMortyRepository,
    application: Application
) : AndroidViewModel(application) {

    private var mCharacterLiveData = MutableLiveData<Characters>()
    val charactersLiveData: LiveData<Characters>
        get() = mCharacterLiveData

    private var mCharacterListLiveData = MutableLiveData<CharactersList>()
    val charactersListLiveData: LiveData<CharactersList>
        get() = mCharacterListLiveData

    private var mEpisodesLiveData = MutableLiveData<Episodes>()
    val episodesLiveData: LiveData<Episodes>
        get() = mEpisodesLiveData


    fun getCharacterNamesAndImages() = viewModelScope.launch {
        repository.getCharacterNamesAndImages().let {  response ->
            if (response.isSuccessful) {
                mCharacterLiveData.postValue(response.body())
            } else {
                Toast.makeText(
                    getApplication<Application>().applicationContext,
                    getApplication<Application>().applicationContext.resources.getString(R.string.character_request_error),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


    fun getCharacterNamesAndImagesById(ids: String) = viewModelScope.launch {
        repository.getCharacterNamesAndImagesById(ids).let {  response ->
            if (response.isSuccessful) {
                mCharacterListLiveData.postValue(response.body())
            } else {
                Toast.makeText(
                    getApplication<Application>().applicationContext,
                    getApplication<Application>().applicationContext.resources.getString(R.string.character_request_error),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun getEpisodes(id: Int) = viewModelScope.launch {
        repository.getEpisodes(id).let {  response ->
            if (response.isSuccessful) {
                mEpisodesLiveData.postValue(response.body())
            } else {
                Toast.makeText(
                    getApplication<Application>().applicationContext,
                    getApplication<Application>().applicationContext.resources.getString(R.string.episodes_request_error),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}