package dan.app.movieapp.ui.genresScreen

import com.google.gson.annotations.SerializedName

class GenresListResponse(
    @SerializedName("genres")
    var genres: List<GenreResponse>){}