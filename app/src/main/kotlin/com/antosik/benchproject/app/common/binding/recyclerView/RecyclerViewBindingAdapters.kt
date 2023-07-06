package com.antosik.benchproject.app.common.binding.recyclerView

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antosik.benchproject.app.common.recyclerView.RecyclerBaseAdapter

@Suppress("UNCHECKED_CAST")
@BindingAdapter("adapterData")
fun RecyclerView.updateAdapterData(data: List<Any>?) {
    if (data != null) {
        (adapter as RecyclerBaseAdapter<Any>).uploadData(data)
    }
}
