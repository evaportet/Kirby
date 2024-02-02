package cdi.kirby

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private val requestNotificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()) { isGranted ->
        OnNotificationPermissionResponse(isGranted)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyApp.get().context = this

        AskNotificationPermission()
    }

    fun OnNotificationPermissionResponse( isGranted: Boolean){
        if (isGranted){
            FirebaseMessaging.getInstance().token
                .addOnSuccessListener { token ->
                    Log.e("Token", "Token::> " + token)
                }
                .addOnFailureListener {
                    //TODO ERROR
                }
        } else {
            //TODO ERROR
        }
    }

    fun AskNotificationPermission(){

        val permission = Manifest.permission.POST_NOTIFICATIONS

        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED){
            FirebaseMessaging.getInstance().token
                .addOnSuccessListener { token ->
                    Log.e("Token", "Token::> " + token)
                }
                .addOnFailureListener {
                    //TODO ERROR
                }
        } else if(shouldShowRequestPermissionRationale(permission)){
            //Move To Setup Screen, can show pop up
        } else {
            requestNotificationPermissionLauncher.launch(permission)
        }
    }
}