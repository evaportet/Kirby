package cdi.kirby

import android.content.Context
import android.content.SharedPreferences
import cdi.kirby.clases.data.GameData

object SharedPreferencesManager {

    private const val MyPreferencesName = "MySharedPreferencesStorage"

    private val shared: SharedPreferences by lazy { MyApp.get().getSharedPreferences(
        MyPreferencesName, Context.MODE_PRIVATE) }

    private val editor: SharedPreferences.Editor by lazy { shared.edit() }

    var backgroundColor : Int = MyApp.get().context.resources.getColor(R.color.pink)

    lateinit var gameData : GameData

    var gamesDescription : Boolean = false

}