package com.example.courses.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val topicNameId: Int,
    val students: Int,
    @DrawableRes val imageId: Int
)
