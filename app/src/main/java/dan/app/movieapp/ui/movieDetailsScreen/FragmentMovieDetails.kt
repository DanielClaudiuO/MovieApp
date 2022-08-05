package dan.app.movieapp.ui.movieDetailsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dan.app.movieapp.R
import dan.app.movieapp.databinding.FragmentHomeBinding
import dan.app.movieapp.databinding.FragmentMovieDetailsBinding
import dan.app.movieapp.ui.actorsScreen.ActorRepository
import dan.app.movieapp.ui.genresScreen.Genre
import dan.app.movieapp.ui.genresScreen.GenreRepository
import dan.app.movieapp.ui.searchScreen.ui.home.HomeViewModel
import dan.app.movieapp.ui.searchScreen.ui.home.Movie
import dan.app.movieapp.ui.searchScreen.ui.home.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentMovieDetails : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null

    private val binding get() = _binding!!

    private var genres : List<Genre> = emptyList()

    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

         viewModel = ViewModelProvider(requireActivity())[MovieDetailsViewModel::class.java]

        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch(Dispatchers.IO) {
            viewModel.movie = viewModel.getMovieDetails()
            withContext(Dispatchers.Main) {
                binding.tvMovieTitle.text = viewModel.movie?.title
                binding.tvMovieDate.text = viewModel.movie?.release_date
                binding.tvMovieDescription.text = viewModel.movie?.overview
                binding.tvMovieBudget.text = viewModel.movie?.budget.toString()
                binding.tvVoteAverage.text = viewModel.movie?.vote_average.toString()
                binding.tvVoteCount.text = viewModel.movie?.vote_count.toString()
                genres= viewModel.movie?.genres!!
                loadYtbVideo()
                setupRecyclerView()
            }
        }

    }

    private fun loadYtbVideo() {
        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                viewModel.movie?.videos?.movies?.get(0)
                    ?.let { youTubePlayer.loadVideo(findYoutubeTrailer(), 0f) }
            }
        })
    }

    private fun findYoutubeTrailer(): String {
        viewModel.movie?.videos?.movies?.let { videoList ->
            for (video in videoList) {
                if (video.type == "Trailer")
                    return video.key
            }
        }
        return ""
    }

    private fun setupRecyclerView() {
        val rvGenresMovie = binding.rvDetailsGenres
        rvGenresMovie.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvGenresMovie.adapter = MovieDetailsGenresAdapter(genres)
    }
}