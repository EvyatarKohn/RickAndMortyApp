package com.evyatar.rickandmorty.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.evyatar.rickandmorty.MainListener
import com.evyatar.rickandmorty.R
import com.evyatar.rickandmorty.model.charactermodel.Result
import com.evyatar.rickandmorty.ui.adapters.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private val mViewModel: MainViewModel by viewModels()
    private lateinit var mMainListener: MainListener
    private lateinit var mCharacterAdapter: CharacterAdapter

    companion object {
        fun newInstance(mainListener: MainListener) = CharactersFragment().apply {
            mMainListener = mainListener
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.main_fragment, container, false)

        mViewModel.getCharacterNamesAndImages()

        mViewModel.charactersLiveData.observe(viewLifecycleOwner, Observer { character ->
            setCharacterAdapter(character.results)
        })

        return v
    }

    private fun setCharacterAdapter(charactersList: List<Result>) {
        mCharacterAdapter = CharacterAdapter(charactersList, mMainListener)
        val layoutManager = LinearLayoutManager(activity?.applicationContext)
        characters_recycler_view.layoutManager = layoutManager
        characters_recycler_view.adapter = mCharacterAdapter    }

}