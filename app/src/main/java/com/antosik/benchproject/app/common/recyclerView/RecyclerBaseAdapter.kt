package com.antosik.benchproject.app.common.recyclerView

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerBaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private var data = emptyList<T>()

    // TODO Redundant, in child class you could just override onCreateViewHolder
    abstract fun setViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T>

    @SuppressLint("NotifyDataSetChanged")
    fun uploadData(newData: List<T>) {
        data = newData
        notifyDataSetChanged()
    }

    // TODO = instead of { }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return setViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(data[position])
    }

    // TODO Drop type + new line
    override fun getItemCount(): Int = data.size
}

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: T)
}
