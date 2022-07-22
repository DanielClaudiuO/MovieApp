package dan.app.movieapp.ui.onboardScreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import dan.app.movieapp.R
import dan.app.movieapp.ui.genresScreen.GenresScreenActivity

class OnboardScreenActivity : AppCompatActivity() {

    companion object {
        fun open(context: Context) {
            context.startActivity(Intent(context, OnboardScreenActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onbording_screen)

        setClickListeners()

    }

    private fun setClickListeners(){

        val btnGeneres: Button=findViewById(R.id.btnGeneres)
        btnGeneres.setOnClickListener{
            startActivity(Intent(this, GenresScreenActivity:: class.java))

        }
    }
}