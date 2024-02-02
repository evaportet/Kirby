package cdi.kirby.fragments.screens

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import cdi.kirby.R
import cdi.kirby.SharedPreferencesManager
import cdi.kirby.clases.MyFirebase
import cdi.kirby.fragments.components.AppNavHost
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class Game_description_screen : Fragment() {

    lateinit var fragmentView : View
    val image by lazy { fragmentView.findViewById<ShapeableImageView>(R.id.game_description_image) }
    val yearRelease by lazy { fragmentView.findViewById<TextView>(R.id.year_release) }
    val platformRelease by lazy { fragmentView.findViewById<TextView>(R.id.platform_release) }
    val description by lazy { fragmentView.findViewById<TextView>(R.id.game_description_text) }
    val backButton by lazy { fragmentView.findViewById<ImageButton>(R.id.back_button) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.game_description_screen, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameData = SharedPreferencesManager.gameData

        val timeInSeconds = (System.currentTimeMillis() - gameData.releaseDate) / 1000

        val year = timeInSeconds / 31556952

        MyFirebase.storage.loadImage("/games/${gameData.imagePath}").downloadUrl
            .addOnSuccessListener { uri ->
                loadImage(image, uri)
            }
            .addOnFailureListener {
                cdi.kirby.clases.firebase.MyFirebase.crashlytics.logSimpleError("Load Image Error"){
                    key("Image", "/games/${gameData.imagePath}")
                }
            }

        yearRelease.text = "Year : $year"

        platformRelease.text = "Platform : ${gameData.platform}"

        description.text = gameData.description

        backButton.setOnClickListener {
            SharedPreferencesManager.gamesDescription = false
            AppNavHost.get().navHost.navigate(R.id.transition_description_game)
        }

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