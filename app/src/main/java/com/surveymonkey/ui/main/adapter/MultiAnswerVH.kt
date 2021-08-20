package com.surveymonkey.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surveymonkey.data.model.QuestionPOJO
import com.surveymonkey.databinding.ViewgroupMultiAnswerBinding
import com.surveymonkey.utils.widget.MyCheckBox

class MultiAnswerVH(private val binding: ViewgroupMultiAnswerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(pojo: QuestionPOJO, editable: Boolean, position: Int) {
        binding.containerLl.removeAllViewsInLayout()

        val name = "%d. %s%s".format(position, pojo.name, if (pojo.required) "*" else "")
        binding.nameTxt.text = name

        pojo.variants?.forEach {
            val checkBox = MyCheckBox(binding.root.context)
            checkBox.setPojo(it)
            checkBox.isEnabled = editable
            binding.containerLl.addView(checkBox)
        }
    }

    companion object {
        fun from(parent: ViewGroup): MultiAnswerVH {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ViewgroupMultiAnswerBinding.inflate(inflater, parent, false)

            return MultiAnswerVH(binding)
        }
    }
}