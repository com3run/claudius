package com.surveymonkey.ui.main.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.surveymonkey.data.enums.QuestionType
import com.surveymonkey.data.model.BaseFormModel
import com.surveymonkey.data.model.FormPOJO
import com.surveymonkey.data.model.QuestionPOJO

class FormAdapter(private val editable: Boolean = true) :
    ListAdapter<BaseFormModel, RecyclerView.ViewHolder>(DiffCallBack) {

    private val typeHeader = 0
    private val typeRateAnswer = 1
    private val typeMultiAnswer = 2
    private val typeSingleAnswer = 3
    private val typeNoteAnswer = 4
    private val typeFooter = 5

    var onSaveClick: () -> Unit = {}
    var onChange: (position: Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            typeHeader -> HeaderVH.from(parent)
            typeRateAnswer -> RateAnswerVH.from(parent)
            typeMultiAnswer -> MultiAnswerVH.from(parent)
            typeSingleAnswer -> SingleAnswerVH.from(parent)
            typeNoteAnswer -> NoteAnswerVH.from(parent)
            else -> FooterVH.from(parent)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderVH -> holder.bind(getItem(position) as FormPOJO)
            is RateAnswerVH -> holder.bind(getItem(position) as QuestionPOJO, editable, position)
            is MultiAnswerVH -> holder.bind(getItem(position) as QuestionPOJO, editable, position)
            is SingleAnswerVH -> holder.bind(
                getItem(position) as QuestionPOJO, editable, position, onChange
            )
            is NoteAnswerVH -> holder.bind(getItem(position) as QuestionPOJO, editable, position)
            is FooterVH -> holder.bind(onSaveClick)
        }
    }

    override fun getItemCount(): Int {
        val count = super.getItemCount()
        return if (editable) count + 1 else count
    }

    override fun getItemViewType(position: Int): Int {
        if (editable && itemCount == position + 1)
            return typeFooter

        return if (getItem(position) is FormPOJO)
            typeHeader
        else {
            val item = getItem(position) as QuestionPOJO

            when (item.type) {
                QuestionType.RATE_ANSWER -> typeRateAnswer
                QuestionType.MULTI_ANSWER -> typeMultiAnswer
                QuestionType.SINGLE_ANSWER -> typeSingleAnswer
                else -> typeNoteAnswer
            }
        }
    }

    object DiffCallBack : DiffUtil.ItemCallback<BaseFormModel>() {
        override fun areItemsTheSame(oldItem: BaseFormModel, newItem: BaseFormModel) =
            oldItem.id == oldItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: BaseFormModel, newItem: BaseFormModel) =
            oldItem == oldItem
    }
}