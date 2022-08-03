package dan.app.movieapp.ui.searchScreen.ui.home

class MovieMapper {

    fun map(actorResponse: MovieResponse): Movie {
        return Movie(
            id = actorResponse.id,
            title = actorResponse.title,
            release_date = actorResponse.release_date,
            vote_average = actorResponse.vote_average,
            backdrop_image = actorResponse.backdrop_image,
            overview = actorResponse.overview,
            poster_path = actorResponse.poster_path,
            popularity = actorResponse.popularity,
            vote_count = actorResponse.vote_count,
            isFavourite = false,
            isWatched = false
        )
    }
}