package com.antosik.benchproject.app.more.viewModel

import androidx.lifecycle.ViewModel
import com.antosik.benchproject.app.more.view.MoreFragmentNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(private val moreFragmentNavigator: MoreFragmentNavigator) : ViewModel() {

    fun onLicensesClick() = moreFragmentNavigator.navigateToOssLicensesMenuActivity()
}
