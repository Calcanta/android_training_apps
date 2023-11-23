package com.example.reading_30tipsandquotes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DailyCard(
    @StringRes val dayNumberRes: Int,
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val descriptionRes: Int
)
