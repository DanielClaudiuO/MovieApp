package dan.app.movieapp.ui.actorsScreen

import com.google.gson.annotations.SerializedName

class ActorsListResponse(
    @SerializedName("results")
    var actors: List<ActorResponse>)