package com.surveymonkey.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FormPOJO(
    override var id: Int? = null,
    val name: String?,
): BaseFormModel(), Parcelable