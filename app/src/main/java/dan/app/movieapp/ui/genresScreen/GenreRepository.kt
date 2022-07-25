package dan.app.movieapp.ui.genresScreen

import dan.app.movieapp.network.APIClient

class GenreRepository private constructor() {
    companion object {

        val instance= GenreRepository()
    }

    private val generateRemoteDataSource= GenreRemoteDataSource(APIClient.instance.retrofit)

    fun getAllRemoteGenres()= generateRemoteDataSource.getGenres()
}
