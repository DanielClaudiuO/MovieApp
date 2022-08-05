package dan.app.movieapp.ui.onboardScreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import dan.app.movieapp.R
import dan.app.movieapp.ui.actorsScreen.ActorsScreenActivity
import dan.app.movieapp.ui.genresScreen.GenresScreenActivity
import dan.app.movieapp.ui.searchScreen.SearchScreenActivity

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

    private fun setClickListeners() {

        val btnGeneres: Button = findViewById(R.id.btnGeneres)
        val btnActors: Button = findViewById(R.id.btnActors)
        btnGeneres.setOnClickListener {
            startActivity(Intent(this, GenresScreenActivity::class.java))

        }
        btnActors.setOnClickListener {
            startActivity(Intent(this, ActorsScreenActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        val actorsScreen: ActorsScreenActivity= ActorsScreenActivity()
        val genresScreen: GenresScreenActivity= GenresScreenActivity()
        if (actorsScreen.hasEnteredActors && genresScreen.hasEnteredGenres )
            startActivity(Intent(this, SearchScreenActivity::class.java))

    }
}