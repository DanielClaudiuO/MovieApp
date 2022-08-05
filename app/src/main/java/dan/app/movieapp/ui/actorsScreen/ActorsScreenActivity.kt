package dan.app.movieapp.ui.actorsScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dan.app.movieapp.R
import dan.app.movieapp.ui.genresScreen.Genre
import dan.app.movieapp.ui.genresScreen.GenreRepository
import dan.app.movieapp.ui.onboardScreen.OnboardScreenActivity
import dan.app.movieapp.ui.searchScreen.SearchScreenActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActorsScreenActivity : AppCompatActivity() {

    private var actors: List<Actor> = emptyList()
    private val actorsRepository = ActorRepository.instance
    private val genreRepository = GenreRepository.instance
    var hasEnteredActors = false

    private fun getActors() {
        GlobalScope.launch(Dispatchers.IO) {
            actors = actorsRepository.getAllRemoteActors()
            withContext(Dispatchers.Main) {
                preselectSavedActors()

            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actors_screen)
        getActors()
        setOnClickListeners()

    }

    private fun setOnClickListeners() {
        val btnActorSave: FloatingActionButton = findViewById(R.id.btnActorsSave)
        btnActorSave.setOnClickListener {
            saveActors()
            hasEnteredActors = true
        }
    }

    private fun getSelectedActors(): List<Actor> {
        return actors.filter { actors -> actors.isSelected }
    }

    private fun setupRecyclerView() {
        val revActors = findViewById<RecyclerView>(R.id.rvActors)
        revActors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        revActors.adapter = ActorsAdapter(actors)
    }

    private fun saveActors() {
        GlobalScope.launch(Dispatchers.IO) {
            actorsRepository.deleteAllLocal()
            actorsRepository.saveAllLocal(getSelectedActors())

        }
        isSaved()
    }

    private fun isSaved() {
        GlobalScope.launch(Dispatchers.IO) {
            val genreCount = genreRepository.getCount()
            val actorCount = genreRepository.getCount()
            withContext(Dispatchers.Main) {
                verifyIsSaved(genreCount, actorCount)
            }
        }

    }

    private fun verifyIsSaved(genreCount: Int, actorCount: Int) {
        val isSaved = genreCount > 0 && actorCount > 0
        if (isSaved) SearchScreenActivity.open(this)
        else OnboardScreenActivity.open(this)
    }

    private fun preselectSavedActors() {
        GlobalScope.launch(Dispatchers.IO) {
            val savedActors: List<Actor> = actorsRepository.getAllLocalActors()
            withContext(Dispatchers.Main) {
                actors.forEach { actor -> actor.isSelected = savedActors.contains(actor) }
                setupRecyclerView()
            }
        }

    }
}