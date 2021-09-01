package com.evyatar.rickandmorty.ui

import android.app.Application
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.evyatar.rickandmorty.R
import com.evyatar.rickandmorty.model.charactermodel.Characters
import com.evyatar.rickandmorty.repository.RickAndMortyRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val repository: RickAndMortyRepository,
    application: Application
) : AndroidViewModel(application) {

    private var mCharacterLiveData = MutableLiveData<Characters>()
    val charactersLiveData: LiveData<Characters>
        get() = mCharacterLiveData

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
}