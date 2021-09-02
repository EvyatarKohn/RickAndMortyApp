package com.evyatar.rickandmorty.ui

import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.evyatar.rickandmorty.R
import com.evyatar.rickandmorty.MainListener
import com.evyatar.rickandmorty.model.charactermodel.Result
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.character_info_fragment_layout.*
import android.text.Spanned
import com.evyatar.rickandmorty.model.characterlistmodel.CharactersList
import com.evyatar.rickandmorty.util.CustomTypefaceSpan


@AndroidEntryPoint
class CharacterInfoFragment : Fragment() {

    private lateinit var mCharacterResult: Result
    private lateinit var mCharacterInfo: CharactersList
    private lateinit var mMainListener: MainListener


    companion object {
        fun newInstance(characterInfo: Result, mainListener: MainListener) = CharacterInfoFragment().apply {
            mCharacterResult = characterInfo
            mMainListener = mainListener
        }

        fun newInstance(characterInfo: CharactersList, mainListener: MainListener) = CharacterInfoFragment().apply {
            mCharacterInfo = characterInfo
            mMainListener = mainListener
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        UrlImageViewHelper.setUrlDrawable(character_image, mCharacterResult.image)

        character_name.text = resources.getString(R.string.character_name, mCharacterResult.name)

        val spannableStringCreated = SpannableString(resources.getString(R.string.character_created, mCharacterResult.created.substring(0, 10)))
        spannableStringCreated.setSpan(RelativeSizeSpan(1.25f), 0, 8, 0)
        spannableStringCreated.setSpan(CustomTypefaceSpan("", resources.getFont(R.font.luckiest_guy)), 0, 8, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        spannableStringCreated.setSpan(ForegroundColorSpan(resources.getColor(R.color.black)), 9, spannableStringCreated.length, 0)
        character_created.text = spannableStringCreated

        val spannableStringStatus = SpannableString(resources.getString(R.string.character_status, mCharacterResult.status))
        spannableStringStatus.setSpan(RelativeSizeSpan(1.25f), 0, 7, 0)
        spannableStringStatus.setSpan(CustomTypefaceSpan("", resources.getFont(R.font.luckiest_guy)), 0, 7, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        spannableStringStatus.setSpan(ForegroundColorSpan(resources.getColor(R.color.black)), 8, spannableStringStatus.length, 0)
        character_status.text = spannableStringStatus

        val spannableStringSpecies = SpannableString(resources.getString(R.string.character_species, mCharacterResult.species))
        spannableStringSpecies.setSpan(RelativeSizeSpan(1.25f), 0, 8, 0)
        spannableStringSpecies.setSpan(CustomTypefaceSpan("", resources.getFont(R.font.luckiest_guy)), 0, 8, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        spannableStringSpecies.setSpan(ForegroundColorSpan(resources.getColor(R.color.black)), 9, spannableStringSpecies.length, 0)
        character_species.text = spannableStringSpecies

        val spannableStringGender = SpannableString(resources.getString(R.string.character_gender, mCharacterResult.gender))
        spannableStringGender.setSpan(RelativeSizeSpan(1.25f), 0, 7, 0)
        spannableStringGender.setSpan(CustomTypefaceSpan("", resources.getFont(R.font.luckiest_guy)), 0, 7, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        spannableStringGender.setSpan(ForegroundColorSpan(resources.getColor(R.color.black)), 8, spannableStringGender.length, 0)
        character_gender.text = spannableStringGender

        var newLocationString = mCharacterResult.location.name
        if (newLocationString.length > 19) {
            newLocationString = newLocationString.substring(0, 18) + "\n" + newLocationString.substring(19, newLocationString.length)
        }

        val spannableStringLocation = SpannableString(resources.getString(R.string.character_location, newLocationString))
        spannableStringLocation.setSpan(RelativeSizeSpan(1.25f), 0, 9, 0)
        spannableStringLocation.setSpan(CustomTypefaceSpan("", resources.getFont(R.font.luckiest_guy)), 0, 9, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        spannableStringLocation.setSpan(ForegroundColorSpan(resources.getColor(R.color.black)), 10, spannableStringLocation.length, 0)
        character_location.text = spannableStringLocation

        var newOriginString = mCharacterResult.location.name
        if (newOriginString.length > 19) {
            newOriginString = newOriginString.substring(0, 18) + "\n" + newOriginString.substring(19, newOriginString.length)
        }

        val spannableStringOrigin = SpannableString(resources.getString(R.string.character_origin, newOriginString))
        spannableStringOrigin.setSpan(RelativeSizeSpan(1.25f), 0, 7, 0)
        spannableStringOrigin.setSpan(CustomTypefaceSpan("", resources.getFont(R.font.luckiest_guy)), 0, 7, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        spannableStringOrigin.setSpan(ForegroundColorSpan(resources.getColor(R.color.black)), 8, spannableStringOrigin.length, 0)
        character_origin.text = spannableStringOrigin


        episodes_list_btn.setOnClickListener {
            mMainListener.showEpisodeFragment(mCharacterResult.episode)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.character_info_fragment_layout, container, false)
    }

}