package com.javiermarsicano.moviex.screen.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javiermarsicano.moviex.R
import com.javiermarsicano.moviex.common.loadImageFromUrl
import com.javiermarsicano.moviex.data.model.MovieResult
import com.javiermarsicano.moviex.databinding.ListItemBinding

class MoviesAdapter: ListAdapter<MovieResult, MoviesAdapter.ViewHolder>(DiffMovieCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            getItem(position).let { movieResult ->
                entryComments.text = movieResult.voteCount.toString()
                entryDescription.text = movieResult.overview
                entryTitle.text = movieResult.title
                entryImage.loadImageFromUrl(
                    movieResult.posterPath,
                    placeholder = R.drawable.ic_launcher_foreground,
                    thumbnail = R.drawable.ic_launcher_foreground,
                    crossFade = true
                )
                root.setOnClickListener {
                    val action = MainScreenFragmentDirections.actionMoviesListFragmentToDetailsFragment(movieResult.id)
                    it.findNavController().navigate(action)
                }
            }
        }
    }

    class ViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root)
}

object DiffMovieCallback: DiffUtil.ItemCallback<MovieResult>() {
    override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
        return oldItem.title == newItem.title && oldItem.id == newItem.id
    }
}