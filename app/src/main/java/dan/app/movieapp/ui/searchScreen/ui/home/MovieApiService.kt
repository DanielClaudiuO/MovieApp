package dan.app.movieapp.ui.searchScreen.ui.home

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("/movie/popular")
    fun getMovies(
        @Query("api_key")
        apiKey: String,
        @Query("language")
        language: String  ): Call<MovieListResponse>

}