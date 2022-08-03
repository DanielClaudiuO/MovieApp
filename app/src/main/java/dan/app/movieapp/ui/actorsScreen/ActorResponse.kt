package dan.app.movieapp.ui.actorsScreen

import com.google.gson.annotations.SerializedName

class ActorResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("profile_path")
    var photo: String
    )