package dan.app.movieapp.ui.movieDetailsScreen

import com.google.gson.annotations.SerializedName
import dan.app.movieapp.ui.genresScreen.Genre

class MovieDetailsResponse(
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
    var vote_count: Int,
    @SerializedName("budget")
    var budget: Int,
    var videos: VideoListResponse,

    @SerializedName("genres")
    var genres: List<Genre>,
)