package com.example.benchproject.app.fragment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.benchproject.R
import com.example.benchproject.app.main.viewModel.MainViewModel
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
    ): View? {
        val view = inflater.inflate(R.layout.fragment_1, container, false)
        val mainTextView: TextView = view.findViewById(R.id.text_view_fragment1)
        mainTextView.text = mainViewModel.getMovieData()[1].toString()
        return view
    }
}
