package cdi.kirby
import android.app.Activity
import android.app.Application
import cdi.kirby.clases.firebase.MyFirebase

class MyApp : Application() {
    companion object
    {
        private lateinit var instance : MyApp
        public fun get() = instance
    }

    lateinit var context: Activity

    override fun onCreate() {
        super.onCreate()
        instance = this
        MyFirebase.init(this)
        MyFirebase.analytics.logOpenApp()

    }
}