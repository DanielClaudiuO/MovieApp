package dan.app.movieapp.ui.genresScreen

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class Genre(

    @ColumnInfo(name = "id")
    @PrimaryKey
    @NonNull
    var id: Int,

    @ColumnInfo(name = "title") var name: String,

    @ColumnInfo(name = "vote_average") var isSelected: Boolean

) {
    override fun equals(other: Any?) = (other is Genre) && id == other.id

    override fun toString(): String {
        return "Genre(id=$id, title='$name', vote_average=$isSelected)"
    }
}
