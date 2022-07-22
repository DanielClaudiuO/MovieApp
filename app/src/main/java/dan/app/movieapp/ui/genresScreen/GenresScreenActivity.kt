package dan.app.movieapp.ui.genresScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dan.app.movieapp.R

class GenresScreenActivity : AppCompatActivity() {

    private var genres: List<Genre> = emptyList()

    private fun getGenres(){
        genres= listOf(
            Genre(0, "Action", false),
            Genre(1, "Romance", false),
            Genre(2, "Drama", false),
            Genre(3, "Adventure", false),
            Genre(4, "Sci-Fi", false),
            Genre(5, "Comedy", false)
        )
        setupRecyclerView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genres_screen)
        getGenres()
    }

    private fun setupRecyclerView() {
        val rvGenres = findViewById<RecyclerView>(R.id.rvGenres)
        rvGenres.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvGenres.adapter = GenresAdapter(genres)
    }
}