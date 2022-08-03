package dan.app.movieapp.ui.genresScreen

import dan.app.movieapp.database.Database
import dan.app.movieapp.network.APIClient

class GenreRepository private constructor() {
    companion object {

        val instance= GenreRepository()
    }

    private val generateRemoteDataSource= GenreRemoteDataSource(APIClient.instance.retrofit)
    private val genreLocalDataSource= GenreLocalDataSource(Database.instance)

    fun getAllRemoteGenres()= generateRemoteDataSource.getGenres()

    fun getAllLocalGenres() = genreLocalDataSource.getAll()
    fun saveLocal(genre: Genre) = genreLocalDataSource.save(genre)
    fun saveAllLocal(genres: List<Genre>) = genreLocalDataSource.saveAll(genres)
    fun deleteLocal(genre: Genre) = genreLocalDataSource.delete(genre)
    fun deleteAllLocal() = genreLocalDataSource.deleteAll()
    fun deleteAllLocal(genres: List<Genre>) = genreLocalDataSource.deleteAll(genres)
    fun replaceAllLocal(genres: List<Genre>) = genreLocalDataSource.replaceAll(genres)
    fun getCount()=genreLocalDataSource.getCount()
    fun getAllGenresIds()= genreLocalDataSource.getAllGenresIds()

}
