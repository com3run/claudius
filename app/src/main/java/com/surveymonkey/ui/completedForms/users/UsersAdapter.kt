package com.surveymonkey.ui.completedForms.users

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.surveymonkey.data.model.UserPOJO

class UsersAdapter :
    ListAdapter<UserPOJO, RecyclerView.ViewHolder>(DiffCallBack) {

    var onItemClick: (pojo:UserPOJO) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserItemVH.from(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserItemVH -> holder.bind(getItem(position), onItemClick)
        }
    }

    object DiffCallBack : DiffUtil.ItemCallback<UserPOJO>() {
        override fun areItemsTheSame(oldItem: UserPOJO, newItem: UserPOJO) =
            oldItem.id == oldItem.id

        override fun areContentsTheSame(oldItem: UserPOJO, newItem: UserPOJO) =
            oldItem == oldItem
    }
}