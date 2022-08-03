package dan.app.movieapp.ui.actorsScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dan.app.movieapp.R
import dan.app.movieapp.utils.Constants.IMAGE_URL

class ActorsAdapter(private val actorsList: List<Actor>) :
    RecyclerView.Adapter<ActorsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val actorName: TextView = view.findViewById(R.id.tvActorName)
        val actorItem: ConstraintLayout = view.findViewById(R.id.itemActor)
        val actorImage: ImageView= view.findViewById(R.id.ivActor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_actor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actor = actorsList[position]
        holder.actorName.text = actor.name
        selectActor(holder, actor)
        Glide.with(holder.actorItem.context).load(IMAGE_URL + actor.photo).into(holder.actorImage)

        holder.actorItem.setOnClickListener {
            actor.isSelected = !actor.isSelected
            selectActor(holder, actor)
        }

    }

    private fun selectActor(holder: ViewHolder, actor: Actor) {

        holder.actorItem.background = when (actor.isSelected) {
            true -> ContextCompat.getDrawable(
                holder.actorItem.context, R.drawable.actor_selected_bg
            )
            else -> ContextCompat.getDrawable(
                holder.actorItem.context, R.drawable.actor_bg
            )
        }

    }

    override fun getItemCount() = actorsList.size



}