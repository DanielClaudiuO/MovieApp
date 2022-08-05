package dan.app.movieapp.ui.searchScreen.ui.home

import dan.app.movieapp.database.Database
import dan.app.movieapp.network.APIClient


class MovieRepository private constructor() {
    companion object {

        val instance= MovieRepository()
    }

    private val movieRemoteDataSource= MovieRemoteDataSource(APIClient.instance.retrofit)
    private val movieLocalDataSource= MoviesLocalDataSource(Database.instance)

    fun getAllLocalMovies() = movieLocalDataSource.getAll()
    fun saveLocal(movie: Movie) = movieLocalDataSource.save(movie)
    fun saveAllLocal(movies: List<Movie>) = movieLocalDataSource.saveAll(movies)
    fun deleteLocal(movie: Movie) = movieLocalDataSource.delete(movie)
    fun deleteAllLocal() = movieLocalDataSource.deleteAll()
    fun deleteAllLocal(movies: List<Movie>) = movieLocalDataSource.deleteAll(movies)
    fun replaceAllLocal(movies: List<Movie>) = movieLocalDataSource.replaceAll(movies)
    fun getCount()= movieLocalDataSource.getCount()
    fun getFavourite()= movieLocalDataSource.getFavourite()
    fun getWatched()=movieLocalDataSource.getWatched()
    fun insertOrReplace(movie: Movie) = movieLocalDataSource.insertOrUpdate(movie)

    fun getAllRemoteMovies(withCast: String, withGenres: String): List<Movie> {
        return movieRemoteDataSource.getMovies(withCast, withGenres)
    }
    fun getAllSearchedMovies(query: String): List<Movie> {
        return movieRemoteDataSource.getSearchedMovie(query)
    }

    fun getMovieDetails(movieId: Int) = movieRemoteDataSource.getMovieDetails(movieId)
}
