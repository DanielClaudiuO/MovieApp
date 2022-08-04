package dan.app.movieapp.ui.searchScreen.ui.home

import dan.app.movieapp.database.Database

class MoviesLocalDataSource(database: Database) {

    private val movieDAO: MovieDAO = database.movieAppDatabase.moviesDao()

    fun getAll()= movieDAO. getAll()
    fun save(actor: Movie) = movieDAO.save(actor)
    fun saveAll(actors: List<Movie>) = movieDAO.saveAll(actors)
    fun delete(actor: Movie) = movieDAO.delete(actor)
    fun deleteAll() = movieDAO.deleteAll()
    fun deleteAll(actors: List<Movie>) = movieDAO.deleteAll(actors)
    fun replaceAll(actors: List<Movie>) = movieDAO.replaceAll(actors)
    fun getCount()= movieDAO.getCount()
    fun getFavourite()= movieDAO.getFavourites()
    fun getWatched()=movieDAO.getWatched()
    fun insertOrUpdate(movie: Movie) = movieDAO.insertOrUpdateMovie(movie)



}