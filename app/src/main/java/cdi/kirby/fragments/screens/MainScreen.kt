package cdi.kirby.fragments.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cdi.kirby.R
import cdi.kirby.SharedPreferencesManager

class MainScreen : Fragment() {

    companion object{
        private lateinit var instance: MainScreen

        fun get() = instance
    }

    lateinit var fragmentView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.main_screen, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ChangeBackgroundColor(SharedPreferencesManager.backgroundColor)
    }

    fun ChangeBackgroundColor(color: Int){
        fragmentView.setBackgroundColor(color)
    }
}