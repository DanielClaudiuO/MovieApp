package dan.app.movieapp.ui.searchScreen.ui.home

class MovieMapper {

    fun map(movieResponse: MovieResponse): Movie {
        return Movie(
            id = movieResponse.id,
            title = movieResponse.title,
            release_date = movieResponse.release_date,
            vote_average = movieResponse.vote_average,
            backdrop_image = movieResponse.backdrop_image,
            overview = movieResponse.overview,
            poster_path = movieResponse.poster_path,
            popularity = movieResponse.popularity,
            vote_count = movieResponse.vote_count,
            isFavourite = false,
            isWatched = false
        )
    }
}