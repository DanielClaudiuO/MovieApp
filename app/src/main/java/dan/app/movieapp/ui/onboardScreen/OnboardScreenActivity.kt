package dan.app.movieapp.ui.onboardScreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import dan.app.movieapp.R
import dan.app.movieapp.ui.actorsScreen.ActorRepository
import dan.app.movieapp.ui.actorsScreen.ActorsScreenActivity
import dan.app.movieapp.ui.genresScreen.GenreRepository
import dan.app.movieapp.ui.genresScreen.GenresScreenActivity
import dan.app.movieapp.ui.searchScreen.SearchScreenActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OnboardScreenActivity : AppCompatActivity() {

    private val genreRepository = GenreRepository.instance
    private val actorsRepository = ActorRepository.instance

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

    private fun verifyIfFilterIsSelected(){
        GlobalScope.launch ( Dispatchers.IO ){
            val  genreCount = genreRepository.getCount()
            val  actorCount = actorsRepository.getCount()

            withContext(Dispatchers.Main){
                if(genreCount > 0 && actorCount> 0)
                startActivity(Intent(this@OnboardScreenActivity, SearchScreenActivity::class.java))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        verifyIfFilterIsSelected()
    }
}