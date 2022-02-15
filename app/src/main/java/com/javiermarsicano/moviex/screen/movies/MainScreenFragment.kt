package com.javiermarsicano.moviex.screen.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.javiermarsicano.moviex.base.BaseFragment
import com.javiermarsicano.moviex.databinding.MainScreenFragmentBinding
import timber.log.Timber

class MainScreenFragment : BaseFragment() {

    private var _binding: MainScreenFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<MainScreenViewModel>()

    companion object {
        fun newInstance() = MainScreenFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.moviesObservable.observe(viewLifecycleOwner, {
            Timber.d("Movies list received ${it[0]}")
        })
        viewModel.getTopMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}