package com.javiermarsicano.moviex.screen.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.javiermarsicano.moviex.base.BaseFragment
import com.javiermarsicano.moviex.common.EndlessScrollListener
import com.javiermarsicano.moviex.common.EventObserver
import com.javiermarsicano.moviex.common.StatusViewState
import com.javiermarsicano.moviex.databinding.MainScreenFragmentBinding

private const val FIRST_PAGE = 1

class MainScreenFragment : BaseFragment() {

    private var _binding: MainScreenFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<MainScreenViewModel>()

    private val moviesAdapter: MoviesAdapter = MoviesAdapter(mutableListOf()) //TODO inject using DI

    companion object {
        fun newInstance() = MainScreenFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainScreenFragmentBinding.inflate(inflater, container, false)
        binding.itemsList.apply {
            adapter = moviesAdapter
            val linearLayoutManager = LinearLayoutManager(requireContext())
            layoutManager = linearLayoutManager
            addOnScrollListener(object : EndlessScrollListener(linearLayoutManager, FIRST_PAGE){
                override fun onLoadMore(page: Int) {
                    viewModel.getTopMovies(page)
                }
            })
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.statusObservable.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                is StatusViewState.Loading -> binding.progressLoading.isVisible = true
                is StatusViewState.Content -> binding.progressLoading.isVisible = false
                is StatusViewState.Error -> {
                    binding.progressLoading.isVisible = false
                    showErrorMessage(it.exception.localizedMessage.orEmpty())
                }
            }
        })
        viewModel.moviesObservable.observe(viewLifecycleOwner, {
            moviesAdapter.addItems(it)
        })
        if (savedInstanceState == null) {
            viewModel.getTopMovies(FIRST_PAGE)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}