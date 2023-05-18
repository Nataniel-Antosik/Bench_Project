package com.antosik.benchproject.app.common.customView

import android.content.Context
import android.util.AttributeSet
import android.view.animation.TranslateAnimation
import androidx.core.view.isVisible
import com.google.android.material.bottomnavigation.BottomNavigationView

// TODO "customView" is too generic package name
class BenchBottomNavigationView(context: Context, attrs: AttributeSet) : BottomNavigationView(context, attrs) {

    private val slideUpAnimation: TranslateAnimation = TranslateAnimation(
        AnimationValues.fromXDelta,
        AnimationValues.toXDelta,
        height.toFloat(),
        AnimationValues.toYDelta
    ).apply {
        duration = 500
        fillAfter = true
    }

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
        // TODO You could save the animations as a val in the class to not repeat creating them on every show/hide
        TranslateAnimation(
            AnimationValues.fromXDelta,
            AnimationValues.toXDelta,
            height.toFloat(),
            AnimationValues.toYDelta
        ).let {
            // TODO duration should be moved to some const as it is the same for all two animations
            it.duration = 500
            it.fillAfter = true
            startAnimation(it)
        }
        visibility = VISIBLE
    }

    private fun slideDown() {
        TranslateAnimation(
            AnimationValues.fromXDelta,
            AnimationValues.toXDelta,
            AnimationValues.fromYDelta,
            height.toFloat()
        ).let {
            it.duration = 500
            it.fillAfter = true
            startAnimation(it)
        }
        visibility = GONE
    }

    private object AnimationValues {
        const val fromXDelta = 0f
        const val toXDelta = 0f
        const val fromYDelta = 0f
        const val toYDelta = 0f
    }
}
