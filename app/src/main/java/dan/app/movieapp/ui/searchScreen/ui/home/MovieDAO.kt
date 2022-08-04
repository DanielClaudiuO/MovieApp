package dan.app.movieapp.ui.searchScreen.ui.home

import androidx.room.*

@Dao
interface MovieDAO {


    @Query("SELECT *  FROM movies")
    fun getAll(): List<Movie>

    @Insert
    fun save(movie: Movie)

    @Insert
    fun saveAll(genres: List<Movie>)

    @Delete
    fun delete(movie: Movie)

    @Delete
    fun deleteAll(movies: List<Movie>)

    @Query("DELETE FROM movies")
    fun deleteAll()

    @Transaction
    fun replaceAll(movies: List<Movie>) {
        deleteAll()
        saveAll(movies)
    }

    @Transaction
    fun insertOrUpdateMovie(movie: Movie) {
        insertChooseCategories(movie)
        updateChooseCategories(movie)
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertChooseCategories(movie: Movie)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateChooseCategories(movie: Movie)

    @Query("SELECT COUNT(id) FROM movies")
    fun getCount(): Int

    @Query("SELECT * FROM movies WHERE isFavourite = 1")
    fun getFavourites(): List<Movie>

    @Query("SELECT * FROM movies WHERE isWatched = 1")
    fun getWatched(): List<Movie>
}