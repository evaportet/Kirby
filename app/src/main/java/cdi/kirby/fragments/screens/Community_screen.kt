package cdi.kirby.fragments.screens

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import cdi.kirby.R
import cdi.kirby.clases.MyFirebase
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class Community_screen : Fragment() {

    lateinit var fragmentView : View
    val userName by lazy { fragmentView.findViewById<TextView>(R.id.community_post_username) }
    val title by lazy { fragmentView.findViewById<TextView>(R.id.community_post_title) }
    val image by lazy { fragmentView.findViewById<ShapeableImageView>(R.id.community_post_image) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.community_screen, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userName.text = "LiaMS"

        title.text = "I made this fanart! #Kirby #Fanart"

        MyFirebase.storage.loadImage("/community/kirbyFanArt.jpg").downloadUrl
            .addOnSuccessListener { uri ->
                loadImage(image, uri)
            }
            .addOnFailureListener {
                MyFirebase.crashlytics.logSimpleError("Load Image Error"){
                    key("Image", "/community/kirbyFanArt.jpg")
                }
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