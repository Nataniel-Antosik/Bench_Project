package com.antosik.benchproject.app.common.binding.view

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun View.setVisibility(flag: Boolean) {
    if (flag) this.visibility = View.VISIBLE else this.visibility = View.GONE
}
