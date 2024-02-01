package cdi.kirby.fragments.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import cdi.kirby.MyApp
import cdi.kirby.R
import cdi.kirby.SharedPreferencesManager

class Configuration_screen : Fragment() {

    lateinit var fragmentView : View
    val logOutButton by lazy { fragmentView.findViewById<Button>(R.id.log_out_button) }
    val spinnerBackgroundColor by lazy { fragmentView.findViewById<Spinner>(R.id.spinner_background_color) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.configuration_screen, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var context = MyApp.get().context

        val backgroundColors = resources.getStringArray(R.array.background_colors)

        val spinnerBackgroundColorsAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, backgroundColors)

        spinnerBackgroundColor.adapter = spinnerBackgroundColorsAdapter

        var backgroundColorSelected : Int = SharedPreferencesManager.backgroundColor

        lateinit var value : String

        when(backgroundColorSelected){
            context.resources.getColor(R.color.purple_200) -> {
                value = backgroundColors[0]
            }
            context.resources.getColor(R.color.pink) -> {
                value = backgroundColors[1]
            }
            context.resources.getColor(R.color.yellow) -> {
                value = backgroundColors[2]
            }
        }

        var position = spinnerBackgroundColorsAdapter.getPosition(value)
        spinnerBackgroundColor.setSelection(position)

        spinnerBackgroundColor.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                when(spinnerBackgroundColor.selectedItem.toString()){
                    backgroundColors[0] -> {
                        backgroundColorSelected = context.resources.getColor(R.color.purple_200)
                    }
                    backgroundColors[1] -> {
                        backgroundColorSelected = context.resources.getColor(R.color.pink)
                    }
                    backgroundColors[2] -> {
                        backgroundColorSelected = context.resources.getColor(R.color.yellow)
                    }
                }
                SharedPreferencesManager.backgroundColor = backgroundColorSelected
                MainScreen.get().ChangeBackgroundColor(backgroundColorSelected)
                SecondaryScreen.get().ChangeBackgroundColor(backgroundColorSelected)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                return
            }
            }

        logOutButton.setOnClickListener {
            context.finish()
        }
    }
}