package com.antosik.benchproject.app.common.navigator

import android.content.Context
import androidx.fragment.app.Fragment
import javax.inject.Inject

abstract class Navigator<T : FragmentNavigator> : Fragment() {

    @Inject
    lateinit var navigator: T

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigator.attach(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.detach(this)
    }
}
