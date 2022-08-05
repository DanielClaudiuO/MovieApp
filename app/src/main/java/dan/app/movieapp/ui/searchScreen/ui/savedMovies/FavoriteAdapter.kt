package com.example.movieapp.ui.searchScreen.ui.savedMovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dan.app.movieapp.R
import dan.app.movieapp.ui.searchScreen.ui.home.Movie
import dan.app.movieapp.ui.searchScreen.ui.home.MovieRepository
import dan.app.movieapp.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FavoriteAdapter(private val favouriteMoviesList: MutableList<Movie>) :

    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var favorite: Boolean = false
        var watched: Boolean = false

        val movieItem: LinearLayout = view.findViewById(R.id.itemMovieDelete)
        val movieTitle: TextView = view.findViewById(R.id.tvMovieTitle)
        val movieDate: TextView = view.findViewById(R.id.tvMovieDate)
        val movieDescription: TextView = view.findViewById(R.id.tvMovieDescription)
        val moviePhoto: ImageView = view.findViewById(R.id.iv_movie)
//        val favouriteMovieIcon: ImageButton = view.findViewById(R.id.ibFavouriteMovie)
//        val watchedMovieIcon: ImageButton = view.findViewById(R.id.ibWatchedMovie)

        val itemBtnDelete = view.findViewById<ImageButton>(R.id.ibDeleteMovie)!!
    }

    private val movieRepository: MovieRepository = MovieRepository.instance

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_delete, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = favouriteMoviesList[position]
        holder.movieTitle.text = movie.title
        holder.movieDate.text = movie.release_date
        holder.movieDescription.text = movie.overview
        if (movie.poster_path != null)
            Glide.with(holder.moviePhoto.context)
                .load(Constants.IMAGE_URL_MOVIE + movie.poster_path).into(holder.moviePhoto)


        holder.favorite = favouriteMoviesList[position].isFavourite
        holder.watched = favouriteMoviesList[position].isWatched

        holder.itemBtnDelete.setOnClickListener {
            holder.favorite=false
        }


    holder.itemBtnDelete.setOnClickListener {
        updateItem(favouriteMoviesList[position])
        favouriteMoviesList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, favouriteMoviesList.size)
    }
}

private fun updateItem(movie: Movie) {
    GlobalScope.launch(Dispatchers.IO) {
        val saved = ArrayList(movieRepository.getAllLocalMovies())
        val idx = saved.indexOf(movie)
        if (idx != -1) saved[idx].isFavourite = !saved[idx].isFavourite
        if (!saved[idx].isFavourite && !saved[idx].isWatched) {
            movieRepository.deleteLocal(saved[idx])
            saved.removeAt(idx)
        }
        movieRepository.replaceAllLocal(saved.toList())
    }
}

    override fun getItemCount()= favouriteMoviesList.size

}