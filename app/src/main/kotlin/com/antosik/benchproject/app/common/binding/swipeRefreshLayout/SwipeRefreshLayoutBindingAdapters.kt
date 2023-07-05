package com.antosik.benchproject.app.common.binding.swipeRefreshLayout

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("isRefreshed")
fun SwipeRefreshLayout.isRefreshed(isRefreshed: Boolean) {
    if (isRefreshed) {
        isRefreshing = false
    }
}
