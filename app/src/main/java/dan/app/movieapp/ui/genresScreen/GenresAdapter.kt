package dan.app.movieapp.ui.genresScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import dan.app.movieapp.R

class GenresAdapter(private val genresList: List<Genre>) :
    RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val genreName: TextView = view.findViewById(R.id.tvName)
        val genreItem: ConstraintLayout = view.findViewById(R.id.item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = genresList[position]
        holder.genreName.text = genre.name
        holder.genreItem.setOnClickListener {
            genre.isSelected = !genre.isSelected
            holder.genreName.text = when (genre.isSelected) {
                true -> genre.name + " x"
                else -> genre.name
            }
        }
    }

    override fun getItemCount() = genresList.size


}