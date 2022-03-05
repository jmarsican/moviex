package com.javiermarsicano.moviex.screen.movies

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.javiermarsicano.moviex.base.BaseFragment
import com.javiermarsicano.moviex.data.Resource
import com.javiermarsicano.moviex.databinding.MainScreenFragmentBinding

class MainScreenFragment : BaseFragment() {

    private var _binding: MainScreenFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<MainScreenViewModel>()

    private val adapter: MoviesAdapter = MoviesAdapter() //TODO inject using DI

    companion object {
        fun newInstance() = MainScreenFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainScreenFragmentBinding.inflate(inflater, container, false)
        binding.itemsList.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.moviesObservable.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    binding.progressLoading.isVisible = false
                    adapter.submitList(it.data)
                }
                is Resource.Error -> {
                    binding.progressLoading.isVisible = false
                    AlertDialog.Builder(requireContext())
                        .setTitle("Error")
                        .setMessage(it.e.localizedMessage)
                        .show()
                }
                is Resource.Loading -> binding.progressLoading.isVisible = true
            }
        })
        viewModel.getTopMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}