package dan.app.movieapp.ui.movieDetailsScreen

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video")
class Video(
    var key: String,
    @PrimaryKey
    @NonNull
    var id: String,
    var type: String,
)