package cdi.kirby.clases

import android.app.Application
import com.enti.dostres.cdi.abrahamlopezpamias.modulodosfirebasecdi.R
import com.google.firebase.auth.FirebaseAuth

class MyFirebaseAuth(val appContext: Application) {

    private val firebaseAuth = FirebaseAuth.getInstance()

    fun getUsername() = firebaseAuth.currentUser?.displayName ?: appContext.getString(R.string.unknown_user)

}