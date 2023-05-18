package com.antosik.benchproject.app.more.view

import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat.startActivity
import com.antosik.benchproject.app.common.navigator.FragmentNavigator
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class MoreFragmentNavigator @Inject constructor() : FragmentNavigator() {

    // TODO Simplify name, on the VM side we don't need to know the whole activity name, or even the fact that it is activity, name should be more abstract
    // TODO Don't use `=` for functions returning Unit
    fun navigateToOssLicensesMenuActivity() = requireFragment().let {
        startActivity(it.requireContext(), Intent(it.activity, OssLicensesMenuActivity::class.java), null)
    }

    // TODO Don't use `=` for functions returning Unit
    fun openBrowser(uri: Uri) = CustomTabsIntent
        .Builder()
        .build()
        .launchUrl(requireFragment().requireContext(), uri)
}
