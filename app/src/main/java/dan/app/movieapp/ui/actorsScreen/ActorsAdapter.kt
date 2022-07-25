package dan.app.movieapp.ui.actorsScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import dan.app.movieapp.R

class ActorsAdapter(private val actorsList: List<Actor>) :
    RecyclerView.Adapter<ActorsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val actorName: TextView = view.findViewById(R.id.tvActorName)
        val actorItem: ConstraintLayout = view.findViewById(R.id.itemActor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_actor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actor = actorsList[position]
        holder.actorName.text = actor.name
        holder.actorItem.setOnClickListener {
            actor.isSelected = !actor.isSelected
            holder.actorName.text = when (actor.isSelected) {
                true -> actor.name + " + "
                else -> actor.name
            }
            holder.actorName
        }

    }

    

    override fun getItemCount()= actorsList.size



}