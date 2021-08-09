package com.surveymonkey.ui.saved

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.surveymonkey.data.model.SavedFormPOJO

class SavedFormAdapter :
    ListAdapter<SavedFormPOJO, RecyclerView.ViewHolder>(DiffCallBack) {

    var onItemClick: (pojo:SavedFormPOJO) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SavedFormItemVH.from(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SavedFormItemVH -> holder.bind(getItem(position), onItemClick)
        }
    }

    object DiffCallBack : DiffUtil.ItemCallback<SavedFormPOJO>() {
        override fun areItemsTheSame(oldItem: SavedFormPOJO, newItem: SavedFormPOJO) =
            oldItem.id == oldItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: SavedFormPOJO, newItem: SavedFormPOJO) =
            oldItem == oldItem
    }
}