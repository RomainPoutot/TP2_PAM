package com.example.tp2_tmdb

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load


class FilmListAdapter(val films: List<FilmItem>, val filmListFragment: FilmListFragment) : RecyclerView.Adapter<FilmListAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val mTitleView: TextView?
        val mRatingView: TextView?
        val mOverviewView: TextView?
        val mFilmItemId: TextView?
        val mImageView: ImageView?

        init {
            mTitleView = view.findViewById(R.id.FilmTitle)
            mRatingView = view.findViewById(R.id.FilmRating)
            mOverviewView = view.findViewById(R.id.FilmOverview)
            mFilmItemId = view.findViewById(R.id.filmItemId)
            mImageView = view.findViewById(R.id.FilmImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View
        if (viewType == R.layout.film_item) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.film_item, parent, false)
            view.setOnClickListener {
                val filmId: String = view.findViewById<TextView>(R.id.filmItemId).text.toString()
                filmListFragment.onFilmItemClicked(filmId)
            }
        } else {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.next_page_button, parent, false)
            val nextPageButton = view.findViewById<Button>(R.id.next_page_button)
            val previousPageButton = view.findViewById<Button>(R.id.previous_page_button)
            nextPageButton.setOnClickListener {
                filmListFragment.goToNextPage(view)
            }
            previousPageButton.setOnClickListener {
                filmListFragment.goToPreviousPage(view)
            }
        }

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position != films.size) {
            holder.mTitleView?.text = films[position].title
            holder.mRatingView?.text = films[position].vote_average.toString()
            holder.mOverviewView?.text = films[position].overview
            holder.mFilmItemId?.text = films[position].id.toString()
            holder.mImageView?.load("https://image.tmdb.org/t/p/w300${films[position].poster_path}");
        }
    }

    override fun getItemCount(): Int {
        return films.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == films.size) R.layout.next_page_button else R.layout.film_item
    }
}