package cdi.kirby.fragments.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cdi.kirby.R

class Games_screen : Fragment() {

    lateinit var fragmentView : View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.games_screen, container, false)
        return fragmentView
    }

}