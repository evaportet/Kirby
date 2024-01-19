package cdi.kirby.clases
import android.app.Application
typealias FB = MyFirebase
class MyFirebase {
    companion object {

        lateinit var analytics: MyFirebaseAnalytics
        val crashlytics = MyCrashlytics()
        lateinit var auth: MyFirebaseAuth

        fun init(appContext: Application) {
            analytics = MyFirebaseAnalytics(appContext)
            auth = MyFirebaseAuth(appContext)
        }
    }
}