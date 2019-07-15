package com.javiermarsicano.moviex.views.itemslist

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.javiermarsicano.moviex.MyApplication
import com.javiermarsicano.moviex.R
import com.javiermarsicano.moviex.common.animations.MyScaleInLeftAnimator
import com.javiermarsicano.moviex.common.mvp.BaseMVPFragment
import com.javiermarsicano.moviex.data.models.MovieResult
import kotlinx.android.synthetic.main.fragment_item_list.btn_pop
import kotlinx.android.synthetic.main.fragment_item_list.btn_top
import kotlinx.android.synthetic.main.fragment_item_list.btn_upcoming
import kotlinx.android.synthetic.main.fragment_item_list.view.list

import timber.log.Timber
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ItemsFragment.OnListInteractionListener] interface.
 */
class ItemsFragment : BaseMVPFragment<ItemsView, ItemsPresenter>(), ItemsView {

    @Inject
    lateinit var mPresenter: ItemsPresenterImpl

    private lateinit var mAdapter : MyItemRecyclerViewAdapter

    private var listener: OnListInteractionListener? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId(), container, false)

        val moviesList = view.list

        moviesList.layoutManager = LinearLayoutManager(context)
        mAdapter = MyItemRecyclerViewAdapter(mutableListOf(), object : OnListInteractionListener {
            override fun onListInteraction(item: MovieResult?) {
                if (item != null) getPresenter().getVideo(item)
            }
        })
        moviesList.adapter = mAdapter
        moviesList.itemAnimator = MyScaleInLeftAnimator()

        return view
    }

    override fun getPresenter() = mPresenter

    override fun layoutId() = R.layout.fragment_item_list

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_pop.setOnClickListener{
            mAdapter.clear()
            getPresenter().getPopular()
        }
        btn_top.setOnClickListener {
            mAdapter.clear()
            getPresenter().getTopRated()
        }
        btn_upcoming.setOnClickListener {
            mAdapter.clear()
            getPresenter().getUpcoming()
        }

        if (savedInstanceState != null) {
            val previousItems = getPresenter().getCache()
            mAdapter.setItemsList(previousItems.toMutableList())
        } else {
            getPresenter().getPopular()
        }
    }

    override fun showSearchResult(result: List<MovieResult>?) {
        Timber.i("repo count received  ${result?.size}")
        if (result != null) mAdapter.setItemsList(result.toMutableList())
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnListInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListInteractionListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity.application as MyApplication).component.inject(this)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    interface OnListInteractionListener {
        fun onListInteraction(item: MovieResult?)
    }

    companion object {

        const val TAG = "ITEMS_FRAGMENT"

        // TODO: Customize parameter arguments
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
                ItemsFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}
