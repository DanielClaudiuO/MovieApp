package dan.app.movieapp.app

import android.app.Application
import dan.app.movieapp.database.Database

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        Database.instance.initialise(this)
    }
}