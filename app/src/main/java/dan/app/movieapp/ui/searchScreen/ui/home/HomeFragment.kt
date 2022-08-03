package dan.app.movieapp.ui.searchScreen.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dan.app.movieapp.databinding.FragmentHomeBinding
import dan.app.movieapp.ui.actorsScreen.ActorRepository
import dan.app.movieapp.ui.genresScreen.GenreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private var movies: List<Movie> = emptyList()
    private val movieRepository = MovieRepository.instance
    private val genreRepository = GenreRepository.instance
    private val actorRepository = ActorRepository.instance

    private var genreIds: String = ""
    private var actorIds: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        getParams()
        getSearchedMovie()
        setOnClickListeners()

    }

    private fun getSearchedMovie(){
        val search = binding.svSearch
        search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText?.length!! >= 1) {
                    getSearchedMovies(newText)
                } else
                    getMovies()
                return false
            }
        })
    }

    private fun getParams(){
        GlobalScope.launch (Dispatchers.IO) {
            val savedGenresIds: List<Int> = genreRepository.getAllGenresIds()
            genreIds = savedGenresIds.joinToString(separator = "|") { "$it" }
            val savedActorsIds: List<Int> = actorRepository.getAllActorsIds()
            actorIds = savedActorsIds.joinToString(separator = "|") { "$it" }

            withContext(Dispatchers.Main) {
                getMovies()
            }
        }
    }

    private fun getMovies(){
        GlobalScope.launch (Dispatchers.IO) {
            movies = movieRepository.getAllRemoteMovies(actorIds, genreIds)
            withContext(Dispatchers.Main){
                setupRecyclerView()
            }
        }
    }

    private fun getSearchedMovies(query : String){
        GlobalScope.launch (Dispatchers.IO) {
            movies = movieRepository.getAllSearchedMovies(query)
            withContext(Dispatchers.Main){
                setupRecyclerView()
            }
        }
    }

    private fun setupRecyclerView() {
        val rvMovies = binding.rvMovies
        rvMovies.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvMovies.adapter = MovieAdapter(movies)
    }

    private fun setOnClickListeners() {
        val btnFilter = binding.btnSearch
        val svSearch= binding.svSearch
        btnFilter.setOnClickListener {
            svSearch.visibility=View.VISIBLE
        }


    }

}