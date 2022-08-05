package dan.app.movieapp.ui.movieDetailsScreen

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import dan.app.movieapp.ui.genresScreen.Genre


@Entity(tableName = "moviesDetails")
data class MovieDetails(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "poster_path")
    var poster_path: String?,

    @ColumnInfo(name = "backdrop_path")
    var backdrop_image: String?,

    @ColumnInfo(name = "overview")
    var overview: String?,

    @ColumnInfo(name = "release_date")
    var release_date: String?,

    @ColumnInfo(name = "vote_average")
    var vote_average: Double,

    @ColumnInfo(name = "popularity")
    var popularity: Double,

    @ColumnInfo(name = "vote_count")
    var vote_count: Int,

    @ColumnInfo(name = "budget")
    var budget: Int,

    var videos: VideoListResponse?,

    var genres: List<Genre>,





    ) {
    override fun equals(other: Any?) = (other is MovieDetails) && id == other.id

    override fun toString(): String {
        return "Movie(id=$id, title='$title')"
    }
}
