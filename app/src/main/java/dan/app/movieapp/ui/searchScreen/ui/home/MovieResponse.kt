package dan.app.movieapp.ui.searchScreen.ui.home

import com.google.gson.annotations.SerializedName

class MovieResponse(
        @SerializedName("id")
        var id: Int,
        @SerializedName("title")
        var title: String,
        @SerializedName("release_date")
        var release_date: String,
        @SerializedName("vote_average")
        var vote_average: Double,
        @SerializedName("backdrop_path")
        var backdrop_image: String,
        @SerializedName("overview")
        var overview: String,
        @SerializedName("poster_path")
        var poster_path: String?,
        @SerializedName("popularity")
        var popularity: Double,
        @SerializedName("vote_count")
        var vote_count: Int
)