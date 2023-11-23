package com.example.reading_30tipsandquotes.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.reading_30tipsandquotes.R

val Pacifico = FontFamily(
    Font(R.font.pacifico_regular)
)

val DancingScript = FontFamily(
    Font(R.font.dancingscript_regular),
    Font(R.font.dancingscript_bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = Pacifico,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Pacifico,
        fontWeight = FontWeight.Normal,
        fontSize = 21.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = DancingScript,
        fontWeight = FontWeight.Normal,
        fontSize = 21.sp
    ),
    bodySmall = TextStyle(
        fontFamily = DancingScript,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
)