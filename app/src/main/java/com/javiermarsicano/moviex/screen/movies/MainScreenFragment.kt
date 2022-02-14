package com.javiermarsicano.moviex.screen.movies

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.javiermarsicano.moviex.base.BaseFragment
import com.javiermarsicano.moviex.databinding.MainScreenFragmentBinding

class MainScreenFragment : BaseFragment() {

    private var _binding: MainScreenFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainScreenFragment()
    }

    private lateinit var viewModel: MainScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainScreenViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}