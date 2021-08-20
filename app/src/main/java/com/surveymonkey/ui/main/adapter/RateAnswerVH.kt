package com.surveymonkey.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surveymonkey.data.model.QuestionPOJO
import com.surveymonkey.databinding.ViewgroupRateBinding

class RateAnswerVH(private val binding: ViewgroupRateBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(pojo: QuestionPOJO, editable: Boolean, position: Int) {
        binding.nameTxt.text = "%d. %s".format(position, pojo.name)

        binding.np.setOnValueChangedListener { _, _, newVal -> pojo.rate = newVal }
        binding.np.isScrollerEnabled = editable
        binding.np.value = pojo.rate ?: 5
    }

    companion object {
        fun from(parent: ViewGroup): RateAnswerVH {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ViewgroupRateBinding.inflate(inflater, parent, false)

            return RateAnswerVH(binding)
        }
    }
}