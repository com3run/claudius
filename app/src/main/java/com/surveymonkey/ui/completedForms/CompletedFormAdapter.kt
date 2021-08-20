package com.surveymonkey.ui.completedForms

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.surveymonkey.data.model.FormPOJO

class CompletedFormAdapter :
    ListAdapter<FormPOJO, RecyclerView.ViewHolder>(DiffCallBack) {

    var onItemClick: (pojo:FormPOJO) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CompletedFormItemVH.from(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CompletedFormItemVH -> holder.bind(getItem(position), onItemClick)
        }
    }

    object DiffCallBack : DiffUtil.ItemCallback<FormPOJO>() {
        override fun areItemsTheSame(oldItem: FormPOJO, newItem: FormPOJO) =
            oldItem.id == oldItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: FormPOJO, newItem: FormPOJO) =
            oldItem == oldItem
    }
}