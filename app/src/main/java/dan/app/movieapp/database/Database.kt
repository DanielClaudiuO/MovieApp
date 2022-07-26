package dan.app.movieapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dan.app.movieapp.ui.actorsScreen.Actor
import dan.app.movieapp.ui.actorsScreen.ActorDAO
import dan.app.movieapp.ui.genresScreen.Genre
import dan.app.movieapp.ui.genresScreen.GenreDAO

class Database private constructor() {

    companion object{
        val instance= Database()
    }

    @androidx.room.Database(
        entities = [Genre::class, Actor::class],
        version = 2
    )

    abstract class MovieAppDatabase: RoomDatabase(){
        abstract fun genresDao(): GenreDAO
        abstract fun actorsDao():ActorDAO
    }

    lateinit var movieAppDatabase: MovieAppDatabase
    private set

    fun initialise(context: Context ){
        this.movieAppDatabase= Room.databaseBuilder(
            context,
            MovieAppDatabase::class.java,
            "movie_app.db"
        ). fallbackToDestructiveMigration().build()
    }

}