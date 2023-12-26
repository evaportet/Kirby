package cdi.kirby
import android.app.Application

class MyApp : Application() {
    companion object
    {
        private lateinit var instance : MyApp
        public fun get() = instance
    }
}