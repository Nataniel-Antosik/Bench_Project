package com.antosik.benchproject.app.more.view

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.antosik.benchproject.app.common.navigator.FragmentNavigator
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class MoreFragmentNavigator @Inject constructor() : FragmentNavigator() {

    fun navigateToOssLicensesMenuActivity() = requireFragment().let {
        startActivity(it.requireContext(), Intent(it.activity, OssLicensesMenuActivity::class.java), null)
    }
}
