package com.antosik.benchproject.app.more.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.antosik.benchproject.app.more.view.MoreFragmentNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(private val moreFragmentNavigator: MoreFragmentNavigator) : ViewModel() {

    // TODO Don't use `=` for functions returning Unit
    fun onLicensesClick() = moreFragmentNavigator.navigateToOssLicensesMenuActivity()

    // TODO Don't use `=` for functions returning Unit
    fun onGithubClick() = moreFragmentNavigator.openBrowser(Uri.parse(Urls.GITHUB))

    // TODO Don't use `=` for functions returning Unit
    fun onLinkedinClick() = moreFragmentNavigator.openBrowser(Uri.parse(Urls.LINKEDIN))

    private object Urls {
        const val GITHUB = "https://github.com/Nataniel-Antosik"
        const val LINKEDIN = "https://www.linkedin.com/in/nataniel-antosik-0a3568193/"
    }
}
