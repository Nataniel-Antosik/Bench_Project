package com.antosik.benchproject.app.more.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import com.antosik.benchproject.app.common.navigator.NavigableFragment
import com.antosik.benchproject.app.common.theme.BenchProjectTheme
import com.antosik.benchproject.app.more.view.navigator.MoreFragmentNavigator
import com.antosik.benchproject.app.more.viewModel.MoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreFragment : NavigableFragment<MoreFragmentNavigator>() {

    private val moreViewModel: MoreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            BenchProjectTheme {
                MoreScreen(moreViewModel)
            }
        }
    }
}
