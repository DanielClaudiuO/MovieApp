package dan.app.movieapp.ui.movieDetailsScreen

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "videoResponse")
class VideoListResponse (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("results")
    val movies: List<Video>
){
}