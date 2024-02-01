package cdi.kirby

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {

    private const val MyPreferencesName = "MySharedPreferencesStorage"

    private val shared: SharedPreferences by lazy { MyApp.get().getSharedPreferences(
        MyPreferencesName, Context.MODE_PRIVATE) }

    private val editor: SharedPreferences.Editor by lazy { shared.edit() }

    var backgroundColor : String = "#0xF2788D"

}