package dan.app.movieapp.ui.genresScreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dan.app.movieapp.R
import dan.app.movieapp.ui.actorsScreen.ActorRepository
import dan.app.movieapp.ui.onboardScreen.OnboardScreenActivity
import dan.app.movieapp.ui.searchScreen.SearchScreenActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenresScreenActivity : AppCompatActivity() {

    private val genreRepository = GenreRepository.instance
    private val actorsRepository = ActorRepository.instance
    private var genres: List<Genre> = emptyList()
    var hasEnteredGenres = false

    private fun getGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            genres = genreRepository.getAllRemoteGenres()
            withContext(Dispatchers.Main) {
                preselectSavedGenres()

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genres_screen)
        getGenres()
        setOnClickListeners()

    }

    private fun setOnClickListeners() {
        val btnGenreSave: FloatingActionButton = findViewById(R.id.btnGenreSave)
        btnGenreSave.setOnClickListener {
            saveGenres()
            hasEnteredGenres = true
        }
    }

    private fun getSelectedGenres(): List<Genre> {
        return genres.filter { genre -> genre.isSelected }
    }

    private fun setupRecyclerView() {
        val rvGenres = findViewById<RecyclerView>(R.id.rvGenres)
        rvGenres.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvGenres.adapter = GenresAdapter(genres)
    }

    private fun saveGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            genreRepository.deleteAllLocal()
            genreRepository.saveAllLocal(getSelectedGenres())

        }
        isSaved()
    }

    private fun isSaved() {
        GlobalScope.launch(Dispatchers.IO) {
            val genreCount = genreRepository.getCount()
            val actorCount = actorsRepository.getCount()
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

    private fun preselectSavedGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            val savedGenre: List<Genre> = genreRepository.getAllLocalGenres()
            withContext(Dispatchers.Main) {
                genres.forEach { genre -> genre.isSelected = savedGenre.contains(genre) }
                setupRecyclerView()
            }
        }

    }
}