package dan.app.movieapp.ui.searchScreen.ui.home

import dan.app.movieapp.network.executeAndDeliver
import dan.app.movieapp.utils.Constants.API_KEY
import dan.app.movieapp.utils.Constants.LANGUAGE
import retrofit2.Retrofit

class MovieRemoteDataSource(retrofit: Retrofit) {
    private val apiService: MovieApiService = retrofit.create(MovieApiService::class.java)
    private val movieMapper = MovieMapper()

    fun getMovies(withCast: String, withGenres: String): List<Movie> {
        return apiService.getMovies(API_KEY, LANGUAGE, withCast, withGenres)
            .executeAndDeliver().movies.map { movieMapper.map(it) }
    }

    fun getSearchedMovie(query: String): List<Movie> {
        return apiService.getSearchedMovie(API_KEY, LANGUAGE, query)
            .executeAndDeliver()
            .movies
            .map { movieMapper.map(it) }
    }
}