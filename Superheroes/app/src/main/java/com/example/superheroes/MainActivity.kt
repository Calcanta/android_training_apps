package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                val heroesScreen = HeroesScreen()
                heroesScreen.HeroApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuperHeroesPreview() {
    SuperheroesTheme {
        val heroesScreen = HeroesScreen()
        heroesScreen.HeroApp()
    }
}