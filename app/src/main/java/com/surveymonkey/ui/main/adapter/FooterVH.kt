package com.surveymonkey.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surveymonkey.databinding.FormFooterBinding

class FooterVH(private val binding: FormFooterBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(onSaveClick: () -> Unit) {
        binding.saveBtn.setOnClickListener { onSaveClick() }
    }

    companion object {
        fun from(parent: ViewGroup): FooterVH {
            val inflater = LayoutInflater.from(parent.context)
            val binding = FormFooterBinding.inflate(inflater, parent, false)

            return FooterVH(binding)
        }
    }
}