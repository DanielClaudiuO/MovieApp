package dan.app.movieapp.ui.genresScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dan.app.movieapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenresScreenActivity : AppCompatActivity() {

    private val genreRepository= GenreRepository.instance
    private var genres: List<Genre> = emptyList()

    private fun getGenres() {
        GlobalScope.launch(Dispatchers.IO){
            genres=genreRepository.getAllRemoteGenres()
            withContext(Dispatchers.Main){
                setupRecyclerView()

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genres_screen)
        getGenres()
    }

    private fun setupRecyclerView() {
        val rvGenres = findViewById<RecyclerView>(R.id.rvGenres)
        rvGenres.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvGenres.adapter = GenresAdapter(genres)
    }
}