package com.example.tp2_tmdb

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.squareup.picasso.Picasso
import io.ktor.http.*


class FilmItemViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.film_item, parent, false)) {

    fun bind(filmItem: FilmItem) {
        val mTitleView: TextView = itemView.findViewById(R.id.FilmTitle)
        val mRatingView: TextView = itemView.findViewById(R.id.FilmRating)
        val mOverviewView: TextView = itemView.findViewById(R.id.FilmOverview)
        val mImageView: ImageView = itemView.findViewById(R.id.FilmImageView)

        mTitleView.text = filmItem.title
        mRatingView.text = filmItem.vote_average.toString()
        mOverviewView.text = filmItem.overview

        if (filmItem.poster_path != null)
            Picasso.get().load("https://image.tmdb.org/t/p/w300${filmItem.poster_path}").into(mImageView);
    }
}