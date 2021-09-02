package com.evyatar.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.evyatar.rickandmorty.MainListener
import com.evyatar.rickandmorty.R
import com.evyatar.rickandmorty.model.characterlistmodel.CharactersList
import com.evyatar.rickandmorty.model.characterlistmodel.CharactersListItem
import com.evyatar.rickandmorty.model.charactermodel.Result
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper
import kotlinx.android.synthetic.main.character_item.view.*
import kotlinx.android.synthetic.main.episodes_item.view.*

class CharacterListAdapter(
    private var charactersList: CharactersList,
    private val mainListener: MainListener
) : RecyclerView.Adapter<CharacterListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        return CharacterListViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        holder.bind(charactersList[position], mainListener)
    }

    override fun getItemCount() = charactersList.size

}

class CharacterListViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.character_item, parent, false)) {

    private var mCharacterImage: ImageView? = null
    private var mCharacterName: TextView? = null

    init {
        mCharacterImage = itemView.character_image
        mCharacterName = itemView.character_name
    }


    fun bind(charactersList: Result, mainListener: MainListener) {
        UrlImageViewHelper.setUrlDrawable(mCharacterImage, charactersList.image)
        mCharacterName?.text = charactersList.name
        itemView.setOnClickListener {
            mainListener.showCharacterInfoFragment(charactersList)
        }
    }
}
