package com.antosik.benchproject.app.common.navigator

import androidx.fragment.app.Fragment
import com.antosik.benchproject.R
import com.google.android.material.snackbar.Snackbar

abstract class FragmentNavigator {

    var fragment: Fragment? = null

    fun attach(fragment: Fragment) {
        this.fragment = fragment
    }

    fun detach(fragment: Fragment) {
        if (this.fragment === fragment)
            this.fragment = null
    }

    fun errorSnackBar(messageId: Int, onAction: () -> Unit) {
        val fragment = fragment!!
        Snackbar.make(fragment.requireView(), fragment.getString(messageId), Snackbar.LENGTH_INDEFINITE)
            .setAction(fragment.getString(R.string.snackbarRetry)) { onAction() }
            .show()
    }

    fun snackBarMessage(messageId: Int) {
        val fragment = fragment!!
        Snackbar.make(fragment.requireView(), fragment.getString(messageId), Snackbar.LENGTH_SHORT).show()
    }
}
