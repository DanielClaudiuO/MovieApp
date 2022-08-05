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

class WatchedFragment: Fragment(R.layout.fragment_watched) {

    private var movies: MutableList<Movie> = mutableListOf()
    private val movieRepository = MovieRepository.instance

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFavoriteMovies(view)
    }

    private fun getFavoriteMovies(view: View){
        GlobalScope.launch (Dispatchers.IO) {
            movies = movieRepository.getWatched().toMutableList()
            withContext(Dispatchers.Main){
                preselectMovies(view)
            }
        }
    }

    private fun setupRecyclerView(view: View){
        val rvWatchedMovies = view?.findViewById<RecyclerView>(R.id.rvWatchedMovies)
        rvWatchedMovies?.layoutManager=
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        rvWatchedMovies?.adapter = WatchedAdapter(movies)
    }

    private fun preselectMovies(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            val saved: List<Movie> = movieRepository.getAllLocalMovies()
            withContext(Dispatchers.Main) {
                movies.forEach {
                    val idx = saved.indexOf(it)
                    it.isFavourite = (idx != -1) && saved[idx].isFavourite
                    it.isWatched = (idx != -1) && saved[idx].isWatched
                }
                setupRecyclerView(view)
            }
        }
    }


}