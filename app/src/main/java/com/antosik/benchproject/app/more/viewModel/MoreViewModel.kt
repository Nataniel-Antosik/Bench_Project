package com.antosik.benchproject.app.more.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.antosik.benchproject.app.more.view.MoreFragmentNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(private val moreFragmentNavigator: MoreFragmentNavigator) : ViewModel() {

    fun onLicensesClick() = moreFragmentNavigator.navigateToLicenses()

    fun onGithubClick() = moreFragmentNavigator.openBrowser(Uri.parse(Urls.GITHUB))

    fun onLinkedinClick() = moreFragmentNavigator.openBrowser(Uri.parse(Urls.LINKEDIN))

    private object Urls {
        const val GITHUB = "https://github.com/Nataniel-Antosik"
        const val LINKEDIN = "https://www.linkedin.com/in/nataniel-antosik-0a3568193/"
    }
}
