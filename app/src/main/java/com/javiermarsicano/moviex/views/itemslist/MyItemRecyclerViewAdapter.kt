package com.javiermarsicano.moviex.views.itemslist

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.javiermarsicano.moviex.R
import com.javiermarsicano.moviex.common.extensions.BASE_POSTER_URL
import com.javiermarsicano.moviex.common.extensions.setImageUrl
import com.javiermarsicano.moviex.data.models.MovieResult


import com.javiermarsicano.moviex.views.itemslist.ItemsFragment.OnListInteractionListener
import kotlinx.android.synthetic.main.entry_item.view.entry_comments
import kotlinx.android.synthetic.main.entry_item.view.entry_date
import kotlinx.android.synthetic.main.entry_item.view.entry_description
import kotlinx.android.synthetic.main.entry_item.view.entry_image
import kotlinx.android.synthetic.main.entry_item.view.entry_title

class MyItemRecyclerViewAdapter(
        private var mValues: MutableList<MovieResult> = mutableListOf(),
        private val mListener: OnListInteractionListener?)
    : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private lateinit var mContext: Context

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as MovieResult
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListInteraction(item)
        }
    }

    fun setItemsList(newValues: MutableList<MovieResult>) {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return mValues.get(oldItemPosition).id == newValues[newItemPosition].id
                }

                override fun getOldListSize() = mValues.size

                override fun getNewListSize() = newValues.size

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val newItem = newValues[newItemPosition]
                    val oldItem = mValues.get(oldItemPosition)
                    return (newItem.id == oldItem.id
                            && newItem.overview == oldItem.overview
                            && newItem.originalTitle == oldItem.originalTitle)
                }

            })

            mValues = newValues
            result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.entry_item, parent, false)

        mContext = view.context

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mName.text = item.title
        holder.mDescription.text = item.overview
        holder.mComments.text = mContext.getString(R.string.comments_indicator, item.voteCount)
        holder.mImage.setImageUrl(BASE_POSTER_URL+item.backdropPath,crossFade = true, error = R.mipmap.ic_launcher, placeholder = R.mipmap.ic_launcher_round)
        holder.mDate.text = item.releaseDate

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mName: TextView = mView.entry_title
        val mDescription: TextView = mView.entry_description
        val mComments: TextView = mView.entry_comments
        val mImage: ImageView = mView.entry_image
        val mDate: TextView = mView.entry_date

        override fun toString(): String {
            return super.toString() + " '" + mDescription.text + "'"
        }
    }
}
