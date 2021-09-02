package com.evyatar.rickandmorty

import com.evyatar.rickandmorty.model.characterlistmodel.CharactersList
import com.evyatar.rickandmorty.model.charactermodel.Result

interface MainListener {
    fun showCharacterInfoFragment(characterInfo: Result)
    fun showCharacterInfoFragmentByID(characterInfo: CharactersList)
    fun showEpisodeFragment(episodesList: List<String>)
    fun showEpisodeDetailsFragment(episodeNumber: String)
    fun showCharacterFragment(characterList: CharactersList)
}