package dan.app.movieapp.ui.movieDetailsScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dan.app.movieapp.R
import dan.app.movieapp.ui.genresScreen.Genre

class MovieDetailsGenresAdapter(private val movieGenresList: List<Genre>) :
RecyclerView.Adapter<MovieDetailsGenresAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvGenres : TextView = view.findViewById(R.id.tvDetailsGenres)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_genres_movie, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = movieGenresList[position]
        holder.tvGenres.text = genre.name
    }

    override fun getItemCount(): Int = movieGenresList.size
}