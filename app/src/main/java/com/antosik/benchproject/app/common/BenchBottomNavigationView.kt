package com.antosik.benchproject.app.common

import android.content.Context
import android.util.AttributeSet
import android.view.animation.TranslateAnimation
import androidx.core.view.isVisible
import com.google.android.material.bottomnavigation.BottomNavigationView

class BenchBottomNavigationView(context: Context, attrs: AttributeSet) : BottomNavigationView(context, attrs) {

    fun show() {
        if (!isVisible) {
            slideUp()
        }
    }

    fun hide() {
        slideDown()
    }

    private fun slideUp() {
        TranslateAnimation(
            AnimationValue.fromXDelta,
            AnimationValue.toXDelta,
            height.toFloat(),
            AnimationValue.toYDelta
        ).let {
            it.duration = 500
            it.fillAfter = true
            startAnimation(it)
        }
        visibility = VISIBLE
    }

    private fun slideDown() {
        TranslateAnimation(
            AnimationValue.fromXDelta,
            AnimationValue.toXDelta,
            AnimationValue.fromYDelta,
            height.toFloat()
        ).let {
            it.duration = 500
            it.fillAfter = true
            startAnimation(it)
        }
        visibility = GONE
    }

    private object AnimationValue {
        const val fromXDelta = 0f
        const val toXDelta = 0f
        const val fromYDelta = 0f
        const val toYDelta = 0f
    }
}
