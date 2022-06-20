package com.example.benchproject.app.common.navigator

import androidx.fragment.app.Fragment
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

    fun errorSnackBar(message: String) {
        Snackbar.make(fragment!!.requireView(), message, Snackbar.LENGTH_SHORT).show()
    }
}
