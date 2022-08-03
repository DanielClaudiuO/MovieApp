package dan.app.movieapp.ui.searchScreen.ui.home

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
        @ColumnInfo(name = "id")
        @PrimaryKey
        @NonNull
        var id: Int,
        @ColumnInfo(name = "title")
        var title: String,
        @ColumnInfo(name = "release_date")
        var release_date: String,
        @ColumnInfo(name = "vote_average")
        var vote_average: Double,
        @ColumnInfo(name = "backdrop_path")
        var backdrop_image: String?,
        @ColumnInfo(name = "overview")
        var overview: String,
        @ColumnInfo(name = "poster_path")
        var poster_path: String?,
        @ColumnInfo(name = "popularity")
        var popularity: Double,
        @ColumnInfo(name = "vote_count")
        var vote_count: Int,
        @ColumnInfo(name = "isWatched")
        var isWatched: Boolean,
        @ColumnInfo(name = "isFavourite")
        var isFavourite: Boolean




        ) {
    override fun equals(other: Any?) = (other is Movie) && id == other.id

    override fun toString(): String {
        return "Movie(id=$id, title='$title', release_date='$release_date', vote_average=$vote_average, backdrop_image='$backdrop_image', overview='$overview', poster_path=$poster_path, popularity=$popularity, vote_count=$vote_count)"
    }


}


