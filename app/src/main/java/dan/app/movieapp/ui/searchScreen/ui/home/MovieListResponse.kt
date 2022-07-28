package dan.app.movieapp.ui.searchScreen.ui.home

import com.google.gson.annotations.SerializedName

class MovieListResponse(
    @SerializedName("results")
    var movies: List<MovieResponse>)