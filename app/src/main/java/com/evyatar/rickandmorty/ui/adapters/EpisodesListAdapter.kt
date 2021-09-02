package com.evyatar.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.evyatar.rickandmorty.MainListener
import com.evyatar.rickandmorty.R
import kotlinx.android.synthetic.main.episodes_item.view.*

class EpisodesListAdapter(
    private var episodesList: List<String>,
    private val mainListener: MainListener
) : RecyclerView.Adapter<EpisodesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.bind(
            episodesList[position].substring(32, 39
            ) + " " + episodesList[position].substring(40, episodesList[position].length),
            mainListener
        )
    }

    override fun getItemCount() = episodesList.size

}

class EpisodesViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.episodes_item, parent, false)) {

    private var mEpisode: TextView? = null

    init {
        mEpisode = itemView.episode
    }

    fun bind(episodeName: String, mainListener: MainListener) {
        mEpisode?.text = episodeName
        itemView.setOnClickListener {
            mainListener.showEpisodeDetailsFragment(episodeName.substring(8, episodeName.length))
        }
    }
}