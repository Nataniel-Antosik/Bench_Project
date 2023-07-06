package com.antosik.benchproject.app.common.binding.view

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun View.setVisibility(flag: Boolean) {
    visibility = if (flag) View.VISIBLE else View.GONE
}
