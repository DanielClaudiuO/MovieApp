package dan.app.movieapp.ui.actorsScreen

import dan.app.movieapp.database.Database
import dan.app.movieapp.network.APIClient


class ActorRepository private constructor() {
    companion object {

        val instance= ActorRepository()
    }

    private val actorRemoteDataSource= ActorsRemoteDataSource(APIClient.instance.retrofit)
    private val actorLocalDataSource= ActorsLocalDataSource(Database.instance)

    fun getAllRemoteActors()= actorRemoteDataSource.getActors()

    fun getAllLocalActors() = actorLocalDataSource.getAll()
    fun saveLocal(actor: Actor) = actorLocalDataSource.save(actor)
    fun saveAllLocal(actors: List<Actor>) = actorLocalDataSource.saveAll(actors)
    fun deleteLocal(actor: Actor) = actorLocalDataSource.delete(actor)
    fun deleteAllLocal() = actorLocalDataSource.deleteAll()
    fun deleteAllLocal(actors: List<Actor>) = actorLocalDataSource.deleteAll(actors)
    fun replaceAllLocal(actors: List<Actor>) = actorLocalDataSource.replaceAll(actors)
    fun getCount()= actorLocalDataSource.getCount()
    fun getAllActorsIds()= actorLocalDataSource.getAllActorsId()

}
