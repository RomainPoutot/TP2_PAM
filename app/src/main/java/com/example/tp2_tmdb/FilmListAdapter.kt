package com.example.tp2_tmdb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FilmListAdapter(val films: List<FilmItem>) : RecyclerView.Adapter<FilmItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FilmItemViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: FilmItemViewHolder, position: Int) {
        val filmItem: FilmItem = films[position]
        holder.bind(filmItem)
    }

    override fun getItemCount(): Int {
        return films.size
    }
}