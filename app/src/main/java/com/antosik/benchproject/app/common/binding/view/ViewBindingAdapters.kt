package com.antosik.benchproject.app.common.binding.view

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun View.setVisibility(flag: Boolean) {
    // TODO swap visibility to make more clearly this method
    if (flag) this.visibility = View.GONE else this.visibility = View.VISIBLE
}
