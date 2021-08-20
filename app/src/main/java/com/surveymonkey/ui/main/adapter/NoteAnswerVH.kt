package com.surveymonkey.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surveymonkey.data.model.QuestionPOJO
import com.surveymonkey.databinding.ViewgroupNoteBinding

class NoteAnswerVH(private val binding: ViewgroupNoteBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(pojo: QuestionPOJO, editable: Boolean, position: Int) {
        val name = "%d. %s%s".format(position, pojo.name, if (pojo.required) "*" else "")
        binding.nameTxt.text = name

        binding.pojo = pojo

        binding.noteEdt.isEnabled = editable
    }

    companion object {
        fun from(parent: ViewGroup): NoteAnswerVH {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ViewgroupNoteBinding.inflate(inflater, parent, false)

            return NoteAnswerVH(binding)
        }
    }
}