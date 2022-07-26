package dan.app.movieapp.ui.genresScreen

import com.google.gson.annotations.SerializedName

class GenreResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String
    )