package com.example.tp2_tmdb

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilmItemViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.film_item, parent, false)) {

    fun bind(filmItem: FilmItem) {
        var mTitleView: TextView = itemView.findViewById(R.id.FilmTitle)
        var mRatingView: TextView = itemView.findViewById(R.id.FilmRating)

        mTitleView?.text = filmItem.title
        mRatingView?.text = filmItem.vote_average.toString()
    }
}