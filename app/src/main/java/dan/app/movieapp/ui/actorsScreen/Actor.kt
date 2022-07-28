package dan.app.movieapp.ui.actorsScreen

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dan.app.movieapp.ui.genresScreen.Genre

@Entity(tableName = "actors")
data class Actor(
    @ColumnInfo(name = "id")
    @PrimaryKey
    @NonNull
    var id:Int,
    @ColumnInfo(name = "title")
    var name: String,
    @ColumnInfo(name = "release_date")
    var photo: String,
    @ColumnInfo(name = "vote_average")
    var isSelected: Boolean
) {
    override fun equals(other: Any?) = (other is Actor) && id == other.id

    override fun toString(): String {
        return "Actor(id=$id, title='$name', release_date='$photo', vote_average=$isSelected)"
    }
}


