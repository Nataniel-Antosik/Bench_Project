package com.antosik.benchproject.app.common.bottomNavigationView

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import com.antosik.benchproject.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class BenchBottomNavigationView(context: Context, attrs: AttributeSet) : BottomNavigationView(context, attrs) {

    private val slideUpAnimation =
        AnimationUtils.loadAnimation(context, R.anim.slide_up)
    private val slideDownAnimation =
        AnimationUtils.loadAnimation(context, R.anim.slide_down)

    fun show() {
        if (!isVisible) {
            slideUp()
        }
    }

    fun hide() {
        slideDown()
    }

    private fun slideUp() {
        startAnimation(slideUpAnimation)
        visibility = VISIBLE
    }

    private fun slideDown() {
        startAnimation(slideDownAnimation)
        visibility = GONE
    }
}
