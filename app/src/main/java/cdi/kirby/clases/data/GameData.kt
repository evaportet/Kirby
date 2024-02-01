package cdi.kirby.clases.data

import android.net.Uri
import java.io.Serializable

data class GameData(val uri : Uri, val title : String, val year : String, val platform : String): Serializable