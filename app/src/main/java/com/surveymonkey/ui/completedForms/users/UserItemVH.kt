package com.surveymonkey.ui.completedForms.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surveymonkey.data.model.UserPOJO
import com.surveymonkey.databinding.ItemUserBinding

class UserItemVH(private val binding: ItemUserBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(pojo: UserPOJO, onItemClick: (pojo: UserPOJO) -> Unit) {
        binding.titleTxt.text = pojo.name

        binding.root.setOnClickListener { onItemClick(pojo) }
    }

    companion object {
        fun from(parent: ViewGroup): UserItemVH {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemUserBinding.inflate(inflater, parent, false)

            return UserItemVH(binding)
        }
    }
}