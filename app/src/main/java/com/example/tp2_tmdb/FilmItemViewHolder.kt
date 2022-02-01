package com.example.tp2_tmdb

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilmItemViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.film_item, parent, false)) {

    fun bind(filmItem: FilmItem) {
        val mTitleView: TextView = itemView.findViewById(R.id.FilmTitle)
        val mRatingView: TextView = itemView.findViewById(R.id.FilmRating)
        val mOverviewView: TextView = itemView.findViewById(R.id.FilmOverview)

        mTitleView.text = filmItem.title
        mRatingView.text = filmItem.vote_average.toString()
        mOverviewView.text = filmItem.overview
    }
}