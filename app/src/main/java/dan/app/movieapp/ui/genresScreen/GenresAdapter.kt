package dan.app.movieapp.ui.genresScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dan.app.movieapp.R

class GenresAdapter(private val genresList: List<Genre>) :
    RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val genreName: TextView = view.findViewById(R.id.tvGenreName)
        val genreItem: ConstraintLayout = view.findViewById(R.id.itemGenre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = genresList[position]
        holder.genreName.text = genre.name
        selectGenre(holder, genre)

        holder.genreItem.setOnClickListener {
            genre.isSelected = !genre.isSelected
            selectGenre(holder, genre)
        }

    }

    private fun selectGenre(holder: ViewHolder, genre: Genre) {

            holder.genreItem.background = when (genre.isSelected) {
                true -> ContextCompat.getDrawable(
                    holder.genreItem.context, R.drawable.genre_selected_bg
                )
                else -> ContextCompat.getDrawable(
                    holder.genreItem.context, R.drawable.genre_bg
                )
            }

    }

    override fun getItemCount() = genresList.size


}