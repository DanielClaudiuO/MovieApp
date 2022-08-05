package dan.app.movieapp.ui.movieDetailsScreen

class MovieDetailMapper {

    fun map(movieDetailsResponse: MovieDetailsResponse): MovieDetails {
        return MovieDetails(
            id = movieDetailsResponse.id,
            title = movieDetailsResponse.title,
            release_date = movieDetailsResponse.release_date,
            vote_average = movieDetailsResponse.vote_average,
            backdrop_image = movieDetailsResponse.backdrop_image,
            overview = movieDetailsResponse.overview,
            poster_path = movieDetailsResponse.poster_path,
            popularity = movieDetailsResponse.popularity,
            vote_count = movieDetailsResponse.vote_count,
            budget = movieDetailsResponse.budget,
            videos = movieDetailsResponse.videos,
            genres = movieDetailsResponse.genres,
        )
    }
}