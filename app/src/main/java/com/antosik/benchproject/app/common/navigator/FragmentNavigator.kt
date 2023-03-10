package com.antosik.benchproject.app.common.navigator

import androidx.fragment.app.Fragment

abstract class FragmentNavigator {

    private var fragment: Fragment? = null

    fun attach(fragment: Fragment) {
        this.fragment = fragment
    }

    fun detach(fragment: Fragment) {
        if (this.fragment === fragment)
            this.fragment = null
    }

    fun requireFragment() = fragment!!
}
