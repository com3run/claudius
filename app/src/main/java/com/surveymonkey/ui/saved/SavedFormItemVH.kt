package com.surveymonkey.ui.saved

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surveymonkey.data.model.SavedFormPOJO
import com.surveymonkey.databinding.ItemSavedFormBinding

class SavedFormItemVH(private val binding: ItemSavedFormBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(pojo: SavedFormPOJO, onItemClick: (pojo: SavedFormPOJO) -> Unit) {
        binding.titleTxt.text = pojo.name

        binding.root.setOnClickListener { onItemClick(pojo) }
    }

    companion object {
        fun from(parent: ViewGroup): SavedFormItemVH {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemSavedFormBinding.inflate(inflater, parent, false)

            return SavedFormItemVH(binding)
        }
    }
}