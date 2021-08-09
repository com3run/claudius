package com.surveymonkey.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surveymonkey.data.model.FormPOJO
import com.surveymonkey.databinding.FormHeaderBinding

class HeaderVH(private val binding: FormHeaderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(pojo: FormPOJO) {
        binding.pojo = pojo
    }

    companion object {
        fun from(parent: ViewGroup): HeaderVH {
            val inflater = LayoutInflater.from(parent.context)
            val binding = FormHeaderBinding.inflate(inflater, parent, false)

            return HeaderVH(binding)
        }
    }
}