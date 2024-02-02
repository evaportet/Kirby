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
import cdi.kirby.clases.firebase.MyFirebase
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class News_screen : Fragment(){

    lateinit var fragmentView : View
    val imageNews1 by lazy { fragmentView.findViewById<ShapeableImageView>(R.id.image_news_1) }
    val imageNews2 by lazy { fragmentView.findViewById<ShapeableImageView>(R.id.image_news_2) }
    val imageNews3 by lazy { fragmentView.findViewById<ShapeableImageView>(R.id.image_news_3) }
    val imageNews4 by lazy { fragmentView.findViewById<ShapeableImageView>(R.id.image_news_4) }

    val textNews1 by lazy { fragmentView.findViewById<TextView>(R.id.text_news_1) }
    val textNews2 by lazy { fragmentView.findViewById<TextView>(R.id.text_news_2) }
    val textNews3 by lazy { fragmentView.findViewById<TextView>(R.id.text_news_3) }
    val textNews4 by lazy { fragmentView.findViewById<TextView>(R.id.text_news_4) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.news_screen, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MyFirebase.storage.loadImage("/kirbyArt.jpg").downloadUrl
            .addOnSuccessListener { uri ->
                loadImage(imageNews1,uri)
            }
            .addOnFailureListener {
                MyFirebase.crashlytics.logSimpleError("Load Image Error"){
                    key("Image", "/news/kirbyFanArt.jpg")
                }
            }

        textNews1.text = "Update on Fighters 2"


        MyFirebase.storage.loadImage("/kirbyBattle.jpg").downloadUrl
            .addOnSuccessListener { uri ->
                loadImage(imageNews2,uri)
            }
            .addOnFailureListener {
                MyFirebase.crashlytics.logSimpleError("Load Image Error"){
                    key("Image", "/news/kirbyBattle.jpg")
                }
            }

        textNews2.text = "NamoCHI speacial art"


        MyFirebase.storage.loadImage("/kirbyGame.jpg").downloadUrl
            .addOnSuccessListener { uri ->
                loadImage(imageNews3,uri)
            }
            .addOnFailureListener {
                MyFirebase.crashlytics.logSimpleError("Load Image Error"){
                    key("Image", "/news/kirbyGame.jpg")
                }
            }

        textNews3.text = "Kirby Tribu is live!"


        MyFirebase.storage.loadImage("/kirbyPaint.jpg").downloadUrl
            .addOnSuccessListener { uri ->
                loadImage(imageNews4,uri)
            }
            .addOnFailureListener {
                MyFirebase.crashlytics.logSimpleError("Load Image Error"){
                    key("Image", "/news/kirbyPaint.jpg")
                }
            }

        textNews4.text = "Mario Bros: Kirby"

    }

    //loading image for our news
    private fun loadImage(image: ShapeableImageView, uri: Uri)
    {
        CoroutineScope(Dispatchers.IO).launch{
            val stream = withContext(Dispatchers.IO){
                URL(uri.toString()).openStream()
            }

            val newsBitMap = BitmapFactory.decodeStream(stream)

            CoroutineScope(Dispatchers.Main).launch {
                image.setImageBitmap(newsBitMap)
                image.visibility = View.VISIBLE
            }
        }
    }
}