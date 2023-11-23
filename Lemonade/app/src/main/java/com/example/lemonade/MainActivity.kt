package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                Lemonade(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun Lemonade(modifier: Modifier) {
    var resourcesNum by remember {
        mutableStateOf(1)
    }
    var currentTaps by remember {
        mutableStateOf(1)
    }

    var displayResources = when (resourcesNum) {
        1 -> listOf<Int>(R.drawable.lemon_tree, R.string.tap_tree, R.string.lemon_tree, 1)
        2 -> listOf<Int>(R.drawable.lemon_squeeze, R.string.tap_lemon, R.string.lemon, (2..4).random())
        3 -> listOf<Int>(R.drawable.lemon_drink, R.string.tap_lemonade, R.string.lemonade_glass, 1)
        else -> listOf<Int>(R.drawable.lemon_restart, R.string.tap_glass, R.string.empty_glass, 1)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(id = displayResources[1]))
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (currentTaps >= displayResources[3]) {
                resourcesNum = when (resourcesNum) {
                    1 -> 2
                    2 -> 3
                    3 -> 4
                    else -> 1
                }
            } else {
                currentTaps++
            }
        }
        ) {
            Image(
                painter = painterResource(id = displayResources[0]),
                contentDescription = stringResource(id = displayResources[2])
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        Lemonade(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}