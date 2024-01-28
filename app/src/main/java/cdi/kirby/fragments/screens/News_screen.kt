package cdi.kirby.fragments.screens
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cdi.kirby.R
import cdi.kirby.clases.MyFirebase
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class News_screen : Fragment(){

    lateinit var fragmentView : View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.news, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MyFirebase.storage.loadImage(/*imagepath*/).downloadUrl
            .addOnSuccessListener { uri ->
                loadImage(/*variable image screen news*/,uri)
            }
            /*.addOnFailureListener(MyFirebase.crashlytics.logSimpleError("New Image Error"){
                key("Image", result)
            })
            */
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