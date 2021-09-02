package com.evyatar.rickandmorty.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.evyatar.rickandmorty.MainListener
import com.evyatar.rickandmorty.R
import com.evyatar.rickandmorty.model.characterlistmodel.CharactersList
import com.evyatar.rickandmorty.model.charactermodel.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainListener {

    companion object {
        private const val THREE_SEC = 3000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.main_activity)
        Handler(Looper.getMainLooper()).postDelayed({
            replaceFragment(CharactersFragment.newInstance(this), "CHARACTER_FRAGMENT")
        }, THREE_SEC)

    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_left,
                R.anim.exit_to_right,
                R.anim.enter_from_right,
                R.anim.exit_to_left
            )
            .replace(R.id.container, fragment, tag)
            .addToBackStack(tag)
            .commitAllowingStateLoss()
    }

    override fun showCharacterInfoFragment(characterInfo: Result) {
        replaceFragment(
            CharacterInfoFragment.newInstance(characterInfo, this),
            "CHARACTER_INFO_FRAGMENT"
        )
    }

    override fun showCharacterInfoFragmentByID(characterInfo: CharactersList) {
        replaceFragment(
            CharacterInfoFragment.newInstance(characterInfo, this),
            "CHARACTER_INFO_FRAGMENT"
        )    }

    override fun showEpisodeFragment(episodesList: List<String>) {
        replaceFragment(EpisodeListFragment.newInstance(episodesList, this), "EPISODES_FRAGMENT")
    }

    override fun showEpisodeDetailsFragment(episodeNumber: String) {
        replaceFragment(EpisodeInfoFragment.newInstance(episodeNumber, this), "EPISODE_INFO_FRAGMENT")
    }

    override fun showCharacterFragment(characterList: CharactersList) {
        replaceFragment(CharactersFragment.newInstance(characterList), "CHARACTER_FRAGMENT")
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            super.onBackPressed()
        }
    }
}