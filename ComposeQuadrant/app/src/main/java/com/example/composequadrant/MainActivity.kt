package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val titles: List<String> = listOf(
                        stringResource(R.string.title_text),
                        stringResource(R.string.title_image),
                        stringResource(R.string.title_row),
                        stringResource(R.string.title_column)
                    )
                    val contents: List<String> = listOf(
                        stringResource(R.string.text_content),
                        stringResource(R.string.image_content),
                        stringResource(R.string.row_content),
                        stringResource(R.string.column_content)
                    )
                    val colors: List<Color> = listOf(
                        Color.Green,
                        Color.Yellow,
                        Color.Cyan,
                        Color.LightGray
                    )
                    QuadrantDisplay(titles, contents, colors)
                }
            }
        }
    }
}

@Composable
fun QuadrantDisplay(titles: List<String>, contents: List<String>, colors: List<Color>) {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.weight(1f)) {
            SimpleDisplay(
                title = titles[0],
                content = contents[0],
                color = colors[0],
                Modifier.weight(1f)
            )
            SimpleDisplay(
                title = titles[1],
                content = contents[1],
                color = colors[1],
                Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            SimpleDisplay(
                title = titles[2],
                content = contents[2],
                color = colors[2],
                Modifier.weight(1f)
            )
            SimpleDisplay(
                title = titles[3],
                content = contents[3],
                color = colors[3],
                Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun SimpleDisplay(title: String, content: String, color: Color, modifier: Modifier) {
        Column(
            modifier = modifier
                .background(color)
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = content,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Justify
            )
        }
    }

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val titles: List<String> = listOf(
        stringResource(R.string.title_text),
        stringResource(R.string.title_image),
        stringResource(R.string.title_row),
        stringResource(R.string.title_column)
    )
    val contents: List<String> = listOf(
        stringResource(R.string.text_content),
        stringResource(R.string.image_content),
        stringResource(R.string.row_content),
        stringResource(R.string.column_content)
    )
    val colors: List<Color> = listOf(
        Color.Green,
        Color.Yellow,
        Color.Cyan,
        Color.LightGray
    )

    ComposeQuadrantTheme {
        QuadrantDisplay(titles, contents, colors)
    }
}