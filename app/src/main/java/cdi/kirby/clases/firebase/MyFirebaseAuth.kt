package cdi.kirby.clases.firebase

import cdi.kirby.clases.firebase.data.DataBaseUser
import com.google.firebase.auth.FirebaseAuth

class MyFirebaseAuth() {

    private val firebaseAuthentication = FirebaseAuth.getInstance()
    private var currentUser: DataBaseUser? = null

    fun IsLoginActive() = GetUser() != null;

    fun SetCurrentUser(newUser: DataBaseUser){
        currentUser = newUser
    }

    fun GetUser() = currentUser

    fun GetAuthenticationDatabaseUser(): DataBaseUser? {

        firebaseAuthentication.currentUser?.let { user ->
            val dataBaseUser = DataBaseUser(
                id = user.uid,
                email = user.email,
                username = user.displayName,
                photoPath = user.photoUrl.toString()
            )
            return dataBaseUser
        }
        return null
    }
}