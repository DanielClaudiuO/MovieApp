package dan.app.movieapp.ui.genresScreen

import dan.app.movieapp.database.Database

class GenreLocalDataSource(database: Database) {

    val genreDAO: GenreDAO = database.movieAppDatabase.genresDao()

    fun getAll()= genreDAO. getAll()
    fun save(genre: Genre) = genreDAO.save(genre)
    fun saveAll(genres: List<Genre>) = genreDAO.saveAll(genres)
    fun delete(genre: Genre) = genreDAO.delete(genre)
    fun deleteAll() = genreDAO.deleteAll()
    fun deleteAll(genres: List<Genre>) = genreDAO.deleteAll(genres)
    fun replaceAll(genres: List<Genre>) = genreDAO.replaceAll(genres)
    fun getCount()= genreDAO.getCount()
    fun getAllGenresIds()= genreDAO.getAllGenresIds()


}