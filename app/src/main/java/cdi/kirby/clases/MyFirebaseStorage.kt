package cdi.kirby.clases

import android.net.Uri
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import java.lang.Exception
import java.nio.file.FileSystems
import java.nio.file.Path

class MyFirebaseStorage {

    private val storage = Firebase.storage
    private val storageRootReference = storage.reference

    private val imagesPath = "Images"
    private val imagesReference = storageRootReference.child(imagesPath)

    ////// at first we wanted to upload images, but then we decided to download them to use on the news and games screen
    /*fun saveImage(uri: Uri, onSuccess: (Uri) -> Unit, onFailure: (Exception) -> Unit){
        val path: Path = FileSystems.getDefault().getPath(uri.path)
        val name = path.fileName.toString()

        val imageReference = imagesReference.child(name)
        imageReference.putFile(uri)
            .addOnSuccessListener { uploadSnapshot ->
                uploadSnapshot.storage.downloadUrl
                    .addOnSuccessListener(onSuccess)
                    .addOnFailureListener(onFailure)
            }
            .addOnFailureListener(onFailure)
    }
     */

    ////// UPLOAD IMAGES




}