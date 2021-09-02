package com.evyatar.rickandmorty.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.evyatar.rickandmorty.MainListener
import com.evyatar.rickandmorty.R
import com.evyatar.rickandmorty.ui.adapters.EpisodesListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.episodes_list_layout.*

@AndroidEntryPoint
class EpisodeListFragment: Fragment() {

    private lateinit var mEpisodesAdapter: EpisodesListAdapter
    private lateinit var mEpisodesList: List<String>
    private lateinit var mMainListener: MainListener

    companion object {
        fun newInstance(episodesList: List<String>, mainListener: MainListener) = EpisodeListFragment().apply {
            mEpisodesList = episodesList
            mMainListener = mainListener
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEpisodeAdapter(mEpisodesList)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.episodes_list_layout, container, false)
    }

    private fun setEpisodeAdapter(episodesList: List<String>) {
        mEpisodesAdapter = EpisodesListAdapter(episodesList, mMainListener)
        val layoutManager = LinearLayoutManager(activity?.applicationContext)
        episodes_recycler_view.layoutManager = layoutManager
        episodes_recycler_view.adapter = mEpisodesAdapter
    }
}