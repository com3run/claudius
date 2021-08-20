package com.surveymonkey.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surveymonkey.data.model.QuestionPOJO
import com.surveymonkey.databinding.ViewgroupSingleAnswerBinding
import com.surveymonkey.utils.widget.MyRadioButton


class SingleAnswerVH(private val binding: ViewgroupSingleAnswerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        pojo: QuestionPOJO,
        editable: Boolean,
        position: Int,
        onChange: (position: Int) -> Unit
    ) {
        binding.radioGroup.removeAllViewsInLayout()

        val name = "%d. %s%s".format(position, pojo.name, if (pojo.required) "*" else "")
        binding.nameTxt.text = name

        pojo.variants?.forEach {
            val radioButton = MyRadioButton(binding.root.context)
            radioButton.setPojo(it)
            radioButton.isEnabled = editable
            radioButton.onChange = { onChange(position) }
            binding.radioGroup.addView(radioButton)
        }
    }

    companion object {
        fun from(parent: ViewGroup): SingleAnswerVH {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ViewgroupSingleAnswerBinding.inflate(inflater, parent, false)

            return SingleAnswerVH(binding)
        }
    }
}