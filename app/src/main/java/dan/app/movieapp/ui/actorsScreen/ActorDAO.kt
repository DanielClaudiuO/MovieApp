package dan.app.movieapp.ui.actorsScreen

import androidx.room.*

@Dao
interface ActorDAO {


    @Query("SELECT *  FROM movies")
    fun getAll(): List<Actor>

    @Insert
    fun save(actor: Actor)

    @Insert
    fun saveAll(genres: List<Actor>)

    @Delete
    fun delete(actor: Actor)

    @Delete
    fun deleteAll(actors: List<Actor>)

    @Query("DELETE FROM movies")
    fun deleteAll()

    @Transaction
    fun replaceAll(actors: List<Actor>){
        deleteAll()
        saveAll(actors)
    }

    @Query("SELECT COUNT(id) FROM movies")
    fun getCount(): Int
}