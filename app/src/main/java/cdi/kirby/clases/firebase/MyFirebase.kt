package cdi.kirby.clases.firebase

import android.app.Application

typealias FB = MyFirebase

class MyFirebase {
    companion object {

        lateinit var analytics: MyFirebaseAnalytics
        val crashlytics = MyCrashlytics()
        val dataBase = MyFirebaseDatabase();
        val storage = MyFirebaseStorage();
        val auth = MyFirebaseAuth()

        fun init(appContext: Application) {
            analytics = MyFirebaseAnalytics(appContext)
        }
    }
}