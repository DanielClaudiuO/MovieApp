package dan.app.movieapp.ui.actorsScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dan.app.movieapp.R

class ActorsScreenActivity : AppCompatActivity() {

    private var actors: List<Actor> = emptyList()

    private fun getActors() {
        actors = listOf(
            Actor(0, "Brad Pitt", false),
            Actor(1, "Gal Gadot", false),
            Actor(2, "Leonardo DiCaprio", false),
            Actor(3, "Benedict Cumberbatch", false),
            Actor(4, "John Krasinski", false),
            Actor(5, "Scarlet Johansson", false)
        )
        setupRecyclerView()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actors_screen)
        getActors()
    }

    private fun setupRecyclerView() {
        val revActors = findViewById<RecyclerView>(R.id.rvActors)
        revActors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        revActors.adapter = ActorsAdapter(actors)
    }
}