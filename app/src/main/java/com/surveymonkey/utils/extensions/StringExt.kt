package com.surveymonkey.utils.extensions

fun String?.isValidName() = this?.trim()?.length ?: 0 > 1
fun String?.isValidUsername() = this?.trim()?.length ?: 0 >= 5

fun isValidNameAndUsername(name: String?, username: String?) =
    name.isValidName() && username.isValidUsername()


fun String?.isValidNote() = this?.trim()?.length ?: 0 >= 15
