package com.example.benchproject.app.fragment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.benchproject.app.main.viewModel.MainViewModel
import com.example.benchproject.databinding.Fragment2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Fragment2 : Fragment() {
    // TODO change fragment 2 name
    // TODO make view model for this fragment
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = Fragment2Binding.inflate(inflater, container, false)
        mainViewModel.getMovieData()
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}
