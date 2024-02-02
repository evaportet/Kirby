package cdi.kirby.clases.viewholder

import android.graphics.BitmapFactory
import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import cdi.kirby.R
import cdi.kirby.clases.MyFirebase
import cdi.kirby.clases.data.GameData
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class GameViewHolder(view : View, var gameData: GameData? = null) : ViewHolder(view) {

    val image by lazy { view.findViewById<ShapeableImageView>(R.id.game_image) }
    val title by lazy { view.findViewById<TextView>(R.id.game_name) }

    fun SetupWithGame(gameData: GameData){

        MyFirebase.storage.loadImage("/games/${gameData.imagePath}").downloadUrl
            .addOnSuccessListener { uri ->
                loadImage(image, uri)
            }
            .addOnFailureListener {
                cdi.kirby.clases.firebase.MyFirebase.crashlytics.logSimpleError("Load Image Error"){
                    key("Image", "/games/${gameData.imagePath}")
                }
            }

        title.text = gameData.title
        this.gameData = gameData
    }

    private fun loadImage(image: ShapeableImageView, uri: Uri){

        CoroutineScope(Dispatchers.IO).launch {
            val stream = withContext(Dispatchers.IO){
                URL(uri.toString()).openStream()
            }
            val profileIconBitMap = BitmapFactory.decodeStream(stream)

            CoroutineScope(Dispatchers.Main).launch {
                image.setImageBitmap(profileIconBitMap)
                image.visibility = View.VISIBLE
            }
        }
    }

}
