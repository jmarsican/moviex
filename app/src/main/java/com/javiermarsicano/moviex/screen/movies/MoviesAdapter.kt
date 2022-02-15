package com.javiermarsicano.moviex.screen.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.javiermarsicano.moviex.R
import com.javiermarsicano.moviex.common.loadImageFromUrl
import com.javiermarsicano.moviex.data.model.MovieResult
import com.javiermarsicano.moviex.databinding.ListItemBinding

class MoviesAdapter(private val itemsList: MutableList<MovieResult>): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            itemsList[position].let {
                entryComments.text = it.voteCount.toString()
                entryDescription.text = it.overview
                entryTitle.text = it.title
                entryImage.loadImageFromUrl(
                    it.posterPath,
                    placeholder = R.drawable.ic_launcher_foreground,
                    thumbnail = R.drawable.ic_launcher_foreground,
                    crossFade = true
                )
            }
        }
    }

    fun updateList(newItems: List<MovieResult>) {
        itemsList.clear()
        itemsList.addAll(newItems)
        notifyDataSetChanged() //TODO use DiffUtil
    }

    override fun getItemCount(): Int = itemsList.size

    class ViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root)
}