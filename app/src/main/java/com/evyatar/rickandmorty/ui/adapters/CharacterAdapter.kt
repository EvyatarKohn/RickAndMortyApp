package com.evyatar.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.evyatar.rickandmorty.MainListener
import com.evyatar.rickandmorty.R
import kotlinx.android.synthetic.main.character_item.view.*

import com.evyatar.rickandmorty.model.charactermodel.Result
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper


class CharacterAdapter(
    private var charactersList: List<Result>,
    private val mainListener: MainListener
) : RecyclerView.Adapter<CharactersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
       holder.bind(charactersList[position], mainListener)
    }

    override fun getItemCount() = charactersList.size
}

class CharactersViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.character_item, parent, false)) {
    private var mCharacterImage: ImageView? = null
    private var mCharacterName: TextView? = null

    init {
        mCharacterImage = itemView.character_image
        mCharacterName = itemView.character_name
    }

    fun bind(
        charactersList: Result,
        mainListener: MainListener
    ) {
        UrlImageViewHelper.setUrlDrawable(mCharacterImage, charactersList.image)
        mCharacterName?.text = charactersList.name
        itemView.setOnClickListener {
            mainListener.showCharacterInfoFragment(charactersList)
        }
    }
}