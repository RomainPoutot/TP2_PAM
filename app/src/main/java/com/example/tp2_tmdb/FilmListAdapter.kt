package com.example.tp2_tmdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.squareup.picasso.Picasso

class FilmListAdapter(val films: List<FilmItem>, val filmListFragment: FilmListFragment) : RecyclerView.Adapter<FilmListAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val mTitleView: TextView
        val mRatingView: TextView
        val mOverviewView: TextView
        val mFilmItemId: TextView
        val mImageView: ImageView

        init {
            mTitleView = itemView.findViewById(R.id.FilmTitle)
            mRatingView = itemView.findViewById(R.id.FilmRating)
            mOverviewView = itemView.findViewById(R.id.FilmOverview)
            mFilmItemId = itemView.findViewById(R.id.filmItemId)
            mImageView = itemView.findViewById(R.id.FilmImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.film_item, parent, false)
        view.setOnClickListener {
                val filmId: String = view.findViewById<TextView>(R.id.filmItemId).text.toString()
                filmListFragment.onFilmItemClicked(filmId)
            }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mTitleView.text = films[position].title
        holder.mRatingView.text = films[position].vote_average.toString()
        holder.mOverviewView.text = films[position].overview
        holder.mFilmItemId.text = films[position].id.toString()
        holder.mImageView.load("https://image.tmdb.org/t/p/w300${films[position].poster_path}");
    }

    override fun getItemCount(): Int {
        return films.size
    }
}