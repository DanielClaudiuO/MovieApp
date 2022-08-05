package dan.app.movieapp.ui.movieDetailsScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dan.app.movieapp.network.APIClient
import dan.app.movieapp.ui.searchScreen.ui.home.Movie
import dan.app.movieapp.ui.searchScreen.ui.home.MovieRemoteDataSource

class MovieDetailsViewModel : ViewModel() {
     val  currentMovieId = MutableLiveData<Int>()
     var movie: MovieDetails?=null
     private val movieRemoteDataSource= MovieRemoteDataSource(APIClient.instance.retrofit)

     fun getMovieDetails(): MovieDetails?{
          return currentMovieId.value?.let { movieRemoteDataSource.getMovieDetails(it) }
     }
}