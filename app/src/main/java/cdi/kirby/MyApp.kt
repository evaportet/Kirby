package cdi.kirby
import android.app.Activity
import android.app.Application

class MyApp : Application() {
    companion object
    {
        private lateinit var instance : MyApp
        public fun get() = instance
    }

    lateinit var context: Activity
}