package dan.app.movieapp.ui.splashScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import dan.app.movieapp.R
import dan.app.movieapp.ui.actorsScreen.ActorRepository
import dan.app.movieapp.ui.genresScreen.GenreRepository
import dan.app.movieapp.ui.onboardScreen.OnboardScreenActivity
import dan.app.movieapp.ui.searchScreen.SearchScreenActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val DELAY = 500L

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private var handler: Handler? = null
    private var runnable: Runnable? = null
    private val genreRepository = GenreRepository.instance
    private val actorsRepository = ActorRepository.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initHandlerToOpenNextActivity()

    }

    private fun initHandlerToOpenNextActivity() {
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            openNextScreen()
        }

        handler?.postDelayed(runnable!!, DELAY)
    }

    private fun openNextScreen() {
        isSaved()

        finish()
    }

    private fun isSaved() {
        GlobalScope.launch(Dispatchers.IO) {
            val genreCount = genreRepository.getCount()
            val actorCount=genreRepository.getCount()
            withContext(Dispatchers.Main) {
                verifyIsSaved(genreCount, actorCount)
            }
        }

    }

    private fun verifyIsSaved(genreCount: Int, actorCount: Int) {
        val isSaved = genreCount > 0 && actorCount > 0
        if (isSaved) SearchScreenActivity.open(this)
        else OnboardScreenActivity.open(this)
    }

    override fun onDestroy() {
        removeHandler()
        super.onDestroy()
    }

    override fun onBackPressed() {
        removeHandler()
        super.onBackPressed()
    }

    private fun removeHandler() {
        if (handler != null && runnable != null) {
            handler?.removeCallbacks(runnable!!)
            runnable = null
            handler = null
        }
    }
}