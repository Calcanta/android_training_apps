package com.example.reading_30tipsandquotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.reading_30tipsandquotes.ui.theme.Reading30TipsAndQuotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Reading30TipsAndQuotesTheme {
                val appScreen = AppScreen()
                appScreen.DailyCardApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DailyCardPreview() {
    Reading30TipsAndQuotesTheme {
        val appScreen = AppScreen()
        appScreen.DailyCardApp()
    }
}