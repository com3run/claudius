package com.surveymonkey.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SavedFormPOJO(
    override var id: Int? = null,
    var name: String?,
    val formId: Long?,
    val userId: Long?
): BaseFormModel(), Parcelable