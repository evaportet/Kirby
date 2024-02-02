package cdi.kirby.clases.data

import android.net.Uri
import java.io.Serializable

data class GameData(val imagePath : String, val title : String, val releaseDate : Int, val platform : String, val description: String): Serializable