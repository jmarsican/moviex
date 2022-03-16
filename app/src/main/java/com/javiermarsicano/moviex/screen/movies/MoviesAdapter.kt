package com.javiermarsicano.moviex.screen.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.javiermarsicano.moviex.R
import com.javiermarsicano.moviex.common.loadImageFromUrl
import com.javiermarsicano.moviex.data.model.MovieResult
import com.javiermarsicano.moviex.databinding.ListItemBinding

class MoviesAdapter(private val itemsList: MutableList<MovieResult>): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            itemsList[position].let { movieResult ->
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

    fun addItems(newItems: List<MovieResult>) {
        val previousSize = itemsList.size
        itemsList.addAll(newItems)
        notifyItemRangeInserted(previousSize, newItems.size)
    }

    fun reset() { //To call when want to refresh for example onSwipe
        itemsList.clear()
    }

    override fun getItemCount(): Int = itemsList.size

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