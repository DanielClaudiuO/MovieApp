package com.example.movieapp.ui.searchScreen.ui.savedMovies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dan.app.movieapp.R
import dan.app.movieapp.ui.searchScreen.ui.home.Movie
import dan.app.movieapp.ui.searchScreen.ui.home.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteFragment: Fragment(R.layout.fragment_favourites) {

    private var movies: MutableList<Movie> = mutableListOf()
    private val movieRepository = MovieRepository.instance

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFavoriteMovies(view)
    }

    private fun getFavoriteMovies(view: View){
        GlobalScope.launch (Dispatchers.IO) {
            movies = movieRepository.getFavourite().toMutableList()
            withContext(Dispatchers.Main){
                setupRecyclerView(view)
            }
        }
    }

    private fun setupRecyclerView(view: View){
        val rvFavoriteMovies = view?.findViewById<RecyclerView>(R.id.rvFavouriteMovies)
        rvFavoriteMovies?.layoutManager=
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        rvFavoriteMovies?.adapter = FavoriteAdapter(movies)
    }
}