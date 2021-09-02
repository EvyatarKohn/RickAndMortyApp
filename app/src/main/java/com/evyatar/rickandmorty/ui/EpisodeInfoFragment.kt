package com.evyatar.rickandmorty.ui

import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.evyatar.rickandmorty.MainViewModel
import com.evyatar.rickandmorty.R
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.evyatar.rickandmorty.MainListener
import com.evyatar.rickandmorty.model.characterlistmodel.CharactersList
import com.evyatar.rickandmorty.ui.adapters.CharacterListAdapter
import com.evyatar.rickandmorty.util.CustomTypefaceSpan
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.character_info_fragment_layout.*
import kotlinx.android.synthetic.main.episode_info_layout.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class EpisodeInfoFragment : Fragment() {

    private val mViewModel: MainViewModel by viewModels()
    private lateinit var mEpisodeNumber: String
    private lateinit var mCharacterListAdapter: CharacterListAdapter
    private lateinit var mMainListener: MainListener

    companion object {
        fun newInstance(episodeNumber: String, mainListener: MainListener) = EpisodeInfoFragment().apply {
            mEpisodeNumber = episodeNumber
            mMainListener = mainListener
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.episode_info_layout, container, false)

        mViewModel.getEpisodes(mEpisodeNumber.toInt())

        mViewModel.episodesLiveData.observe(viewLifecycleOwner, Observer { episodes ->

            episode_name.text = resources.getString(R.string.episode_name, episodes.name, episodes.episode)

            val spannableStringAirDate = SpannableString(resources.getString(R.string.episode_air_date, episodes.airDate))
            spannableStringAirDate.setSpan(RelativeSizeSpan(1.25f), 0, 9, 0)
            spannableStringAirDate.setSpan(CustomTypefaceSpan("", resources.getFont(R.font.luckiest_guy)), 0, 9, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
            spannableStringAirDate.setSpan(ForegroundColorSpan(resources.getColor(R.color.black)), 9, spannableStringAirDate.length, 0)
            episode_air_date.text = spannableStringAirDate

           val  episodeCreateDate = LocalDate.parse(episodes.created.substring(0, 10), DateTimeFormatter.ISO_DATE).dayOfMonth.toString() + "/" +
                    LocalDate.parse(episodes.created.substring(0, 10), DateTimeFormatter.ISO_DATE).monthValue.toString() + "/" +
                    LocalDate.parse(episodes.created.substring(0, 10), DateTimeFormatter.ISO_DATE).year

            val spannableStringDate = SpannableString(resources.getString(R.string.episode_create_date, episodeCreateDate))
            spannableStringDate.setSpan(RelativeSizeSpan(1.25f), 0, 12, 0)
            spannableStringDate.setSpan(CustomTypefaceSpan("", resources.getFont(R.font.luckiest_guy)), 0, 12, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
            spannableStringDate.setSpan(ForegroundColorSpan(resources.getColor(R.color.black)), 12, spannableStringDate.length, 0)
            create_date.text = spannableStringDate

            val episodesNumberList: ArrayList<String> = ArrayList()

            episodes.characters.forEach { characterId ->
                episodesNumberList.add(characterId.substring(42, characterId.length))
            }

            mViewModel.getCharacterNamesAndImagesById(episodesNumberList.toString().substring(1, episodesNumberList.toString().length-1).replace(" ", ""))

            mViewModel.charactersListLiveData.observe(viewLifecycleOwner, Observer { characterList ->
                setCharacterListAdapter(characterList)
            })
        })

        return v
    }
    private fun setCharacterListAdapter(characters: CharactersList) {
        mCharacterListAdapter = CharacterListAdapter(characters, mMainListener)
        val layoutManager = LinearLayoutManager(activity?.applicationContext)
        characters_episode_list_recycler_view.layoutManager = layoutManager
        characters_episode_list_recycler_view.adapter = mCharacterListAdapter
    }
}