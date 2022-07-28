package dan.app.movieapp.ui.searchScreen.ui.home

import dan.app.movieapp.database.Database

class MoviesLocalDataSource(database: Database) {

    val movieDTO: MovieDAO = database.movieAppDatabase.moviesDao()

    fun getAll()= movieDTO. getAll()
    fun save(actor: Movie) = movieDTO.save(actor)
    fun saveAll(actors: List<Movie>) = movieDTO.saveAll(actors)
    fun delete(actor: Movie) = movieDTO.delete(actor)
    fun deleteAll() = movieDTO.deleteAll()
    fun deleteAll(actors: List<Movie>) = movieDTO.deleteAll(actors)
    fun replaceAll(actors: List<Movie>) = movieDTO.replaceAll(actors)
    fun getCount()= movieDTO.getCount()


}