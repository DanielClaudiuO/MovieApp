package dan.app.movieapp.ui.searchScreen.ui.savedMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.ui.searchScreen.ui.savedMovies.FavoriteFragment
import com.example.movieapp.ui.searchScreen.ui.savedMovies.WatchedFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dan.app.movieapp.databinding.FragmentSavedMoviesBinding

class SavedFragment : Fragment() {

    private val tabTitles = arrayOf("Favorite", "Watched")

    private var _binding: FragmentSavedMoviesBinding? = null

    private var adapter: AdapterTabbedPager? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this)[SavedViewModel::class.java]

        _binding = FragmentSavedMoviesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager: ViewPager2 = binding.viewPager
        adapter = activity?.let { AdapterTabbedPager(it) }
        adapter?.addFragment(FavoriteFragment(), tabTitles[0])
        adapter?.addFragment(WatchedFragment(), tabTitles[1])

        viewPager.adapter = adapter
        val tabs: TabLayout = binding.tabs

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = adapter?.getTitle(position)
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}