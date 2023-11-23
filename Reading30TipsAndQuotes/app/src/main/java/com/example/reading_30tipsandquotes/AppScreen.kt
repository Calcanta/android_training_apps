package com.example.reading_30tipsandquotes

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.reading_30tipsandquotes.model.DailyCard
import com.example.reading_30tipsandquotes.model.DailyCardRepository.dailyCards

class AppScreen {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun DailyCardApp() {
        Scaffold(
            topBar = {
                TopAppBar()
            }
        ) {
            LazyColumn(
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            ) {
                items(dailyCards) {
                    CardItem(
                        dailyCard = it,
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }

    @Composable
    fun TopAppBar(modifier: Modifier = Modifier) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

    @Composable
    fun CardItem(dailyCard: DailyCard, modifier: Modifier = Modifier) {
        var expanded by remember {
            mutableStateOf(false)
        }
        val color by animateColorAsState(
            targetValue = if (expanded) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.primaryContainer
        )

        Card(
            modifier = modifier.clip(RoundedCornerShape(16.dp)),
            backgroundColor = color,
            elevation = 10.dp
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(color)
                    .clip(RoundedCornerShape(16.dp))
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(color)
                ) {
                    CardHeader(
                        dayNumberRes = dailyCard.dayNumberRes,
                        titleRes = dailyCard.titleRes,
                        modifier = modifier
                            .background(color)
                            .clip(RoundedCornerShape(16.dp)),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    CardItemButton(expanded = expanded, onClick = { expanded = !expanded })
                }
                CardImage(imageRes = dailyCard.imageRes)
                if (expanded) {
                    CardDesription(descriptionRes = dailyCard.descriptionRes)
                }
            }
        }
    }

    @Composable
    fun CardHeader(
        @StringRes dayNumberRes: Int,
        @StringRes titleRes: Int,
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = modifier,
        ) {
            Text(
                text = stringResource(id = dayNumberRes),
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = stringResource(id = titleRes),
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }

    @Composable
    fun CardImage(@DrawableRes imageRes: Int, modifier: Modifier = Modifier) {
        Image(
            modifier = modifier
                .size(250.dp)
                .clip(RoundedCornerShape(8.dp)),
            painter = painterResource(imageRes),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            contentDescription = null,
        )
    }

    @Composable
    fun CardDesription(
        @StringRes descriptionRes: Int,
        modifier: Modifier = Modifier
    ) {
        Column(modifier = modifier) {
            Text(
                text = stringResource(id = descriptionRes),
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }

    @Composable
    private fun CardItemButton(
        expanded: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                tint = androidx.compose.material.MaterialTheme.colors.secondary,
                contentDescription = stringResource(id = R.string.expand_button_content_description)
            )
        }
    }

}