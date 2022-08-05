package dan.app.movieapp.ui.searchScreen.ui.home

import dan.app.movieapp.ui.movieDetailsScreen.MovieDetails
import dan.app.movieapp.ui.movieDetailsScreen.MovieDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("discover/movie")
    fun getMovies(
        @Query("api_key")
        apiKey: String,
        @Query("language") language: String,
        @Query("with_cast") withCast: String,
        @Query("with_genres") withGenres: String):
         Call<MovieListResponse>

    @GET("search/movie")
    fun getSearchedMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ) : Call<MovieListResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("append_to_response") append_to_response: String
    ) : Call<MovieDetailsResponse>
}