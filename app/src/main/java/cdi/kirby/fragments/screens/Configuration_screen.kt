package cdi.kirby.fragments.screens

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

    lateinit var backgroundColorSelected : String

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

        val backgroundColors = resources.getStringArray(R.array.background_colors)

        backgroundColorSelected = SharedPreferencesManager.backgroundColor

        val spinnerBackgroundColorsAdapter = ArrayAdapter(MyApp.get().context, android.R.layout.simple_spinner_item, backgroundColors)

        spinnerBackgroundColor.adapter = spinnerBackgroundColorsAdapter

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
                        SharedPreferencesManager.backgroundColor = "#FFBB86FC"
                    }
                    backgroundColors[1] -> {
                        SharedPreferencesManager.backgroundColor = "#F2788D"
                    }
                    backgroundColors[2] -> {
                        SharedPreferencesManager.backgroundColor = "#FEE745"
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                return
            }
            }

        logOutButton.setOnClickListener {
            MyApp.get().context.finish()
        }
    }
}