package com.surveymonkey.ui.completedForms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surveymonkey.data.model.FormPOJO
import com.surveymonkey.databinding.ItemSavedFormBinding

class CompletedFormItemVH(private val binding: ItemSavedFormBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(pojo: FormPOJO, onItemClick: (pojo: FormPOJO) -> Unit) {
        binding.titleTxt.text = pojo.name

        binding.root.setOnClickListener { onItemClick(pojo) }
    }

    companion object {
        fun from(parent: ViewGroup): CompletedFormItemVH {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemSavedFormBinding.inflate(inflater, parent, false)

            return CompletedFormItemVH(binding)
        }
    }
}