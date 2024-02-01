package cdi.kirby.fragments.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cdi.kirby.KirbyActivity
import cdi.kirby.MyApp
import cdi.kirby.R
import cdi.kirby.SharedPreferencesManager
import cdi.kirby.clases.firebase.data.DataBaseUser
import cdi.kirby.clases.firebase.MyFirebase
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.gms.common.SignInButton
import java.util.Date

class LogIn_screen : Fragment()
{
    lateinit var fragmentView: View
    val googleAuthButton by lazy { fragmentView.findViewById<SignInButton>(R.id.login_google_button) }

    val signInLauncher = registerForActivityResult(FirebaseAuthUIActivityResultContract()) { res ->
        this.onSignInResult(res)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.login_screen, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentView
        googleAuthButton.setOnClickListener { googleAuth() }
    }

    private fun googleAuth() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        val signIntIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()

        signInLauncher.launch(signIntIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {

        if(result.resultCode != Activity.RESULT_OK) {
            MyFirebase.crashlytics.logSimpleError("Login Error") {
                key("code", result.resultCode)
                key("data", result.toString())
            }
            sendToastError()
            return
        }

        val authUser = MyFirebase.auth.GetAuthenticationDatabaseUser()?: kotlin.run {
            MyFirebase.crashlytics.logSimpleError("Login Error No User") {
                key("code", result.resultCode)
                key("data", result.toString())
            }
            sendToastError()
            return
        }

        val id = authUser.id ?: kotlin.run {
            MyFirebase.crashlytics.logSimpleError("Login Error No ID") {
                key("code", result.resultCode)
                key("data", result.toString())
            }
            sendToastError()
            return
        }

        MyFirebase.dataBase.find<DataBaseUser>(id, authUser.GetTable(),
            onSuccess = { dbUser ->
                dbUser.lastLogin = Date()

                finalSaveUser(dbUser)
            },
            onFailure = {
                finalSaveUser(authUser)
            })
    }

    private fun finalSaveUser(dbUser: DataBaseUser) {
        MyFirebase.dataBase.save(dbUser,
            onSuccess = { dbUser ->
                MyFirebase.auth.SetCurrentUser(dbUser)
                sendToastSuccessAndClose()
                val intent = Intent(MyApp.get().context, KirbyActivity::class.java)
                MyApp.get().context.startActivity(intent)
            },
            onFailure = {
                sendToastError()
            }
        )
    }

    private fun sendToastError() {
        /*Snackbar.make(
            AppDrawer.get().fragmentView,
            getString(R.string.login_error),
            Snackbar.LENGTH_LONG)
            .show()*/
    }

    private fun sendToastSuccessAndClose() {
        /*Snackbar.make(
            AppDrawer.get().fragmentView,
            getString(R.string.user_login_message, MyFirebase.authentication.GetUser()?.username),
            Snackbar.LENGTH_LONG)
            .show()*/
        parentFragmentManager.popBackStack()
    }
}