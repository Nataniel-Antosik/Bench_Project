package com.antosik.benchproject.app.common.recyclerView

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerBaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private var data = emptyList<T>()

    @SuppressLint("NotifyDataSetChanged")
    fun uploadData(newData: List<T>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() =
        data.size
}

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: T)
}
