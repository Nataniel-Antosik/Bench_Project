package com.example.benchproject.app.common.binding.recyclerView

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.benchproject.app.common.recyclerView.BaseAdapter

@Suppress("UNCHECKED_CAST")
@BindingAdapter("adapterData")
fun RecyclerView.updateAdapterData(data: List<Any>) {
    (adapter!! as BaseAdapter<Any>).uploadData(data)
}
