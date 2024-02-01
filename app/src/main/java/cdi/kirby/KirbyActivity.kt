package cdi.kirby

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class KirbyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kirby)

        MyApp.get().context = this
    }
}