package com.example.superheroes

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.HeroesRepository.heroes

class HeroesScreen {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun HeroApp() {
        Scaffold(
            topBar = {
                HeroTopAppBar()
            }
        ) {
            LazyColumn(
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            ) {
                items(heroes) {
                    HeroItem(
                        hero = it,
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }

    @Composable
    fun HeroTopAppBar(modifier: Modifier = Modifier) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

    @Composable
    fun HeroItem(hero: Hero, modifier: Modifier = Modifier) {
        Card(
            modifier = modifier,
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            elevation = 2.dp
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .sizeIn(minHeight = 72.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                HeroInformation(hero.nameRes, hero.descriptionRes)
                Spacer(Modifier.width(16.dp))
                HeroIcon(hero.imageRes)
            }
        }
    }

    @Composable
    fun HeroIcon(@DrawableRes heroIcon: Int, modifier: Modifier = Modifier) {
        Image(
            modifier = modifier
                .size(72.dp)
                .clip(RoundedCornerShape(8.dp)),
            painter = painterResource(heroIcon),
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.TopCenter,
            contentDescription = null,
        )
    }

    @Composable
    fun HeroInformation(
        @StringRes heroName: Int,
        @StringRes heroDescription: Int,
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = modifier
                .height(72.dp)
                .fillMaxWidth(.78f)
        ) {
            Text(
                text = stringResource(heroName),
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = stringResource(heroDescription),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}