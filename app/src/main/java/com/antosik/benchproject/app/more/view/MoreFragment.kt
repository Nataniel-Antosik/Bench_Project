package com.antosik.benchproject.app.more.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.antosik.benchproject.app.common.navigator.NavigableFragment
import com.antosik.benchproject.app.more.viewModel.MoreViewModel
import com.antosik.benchproject.databinding.MoreFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreFragment : NavigableFragment<MoreFragmentNavigator>() {

    private val moreViewModel: MoreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = MoreFragmentBinding.inflate(inflater, container, false).apply {
        lifecycleOwner = viewLifecycleOwner
        viewModel = moreViewModel
    }.root
}
