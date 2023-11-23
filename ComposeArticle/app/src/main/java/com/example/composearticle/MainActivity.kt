package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArticleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ComposeArticleWithImage(
                        stringResource(R.string.article_title),
                        stringResource(R.string.article_intro),
                        stringResource(R.string.article_content)
                    )
                }
            }
        }
    }
}

@Composable
fun ComposeArticleWithImage(title: String, intro: String, content: String) {
    val image = painterResource(id = R.drawable.bg_compose_background)
    Column {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
        )
        ComposeArticleText(title, intro, content)
    }

}

@Composable
fun ComposeArticleText(title: String, intro: String, content: String) {
    Column {
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier
                .wrapContentWidth(align = Alignment.Start)
                .padding(start = 16.dp, top = 16.dp)
        )
        Text(
            text = intro,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.Start)
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = content,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.Start)
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeArticleTheme {
        ComposeArticleWithImage(
            stringResource(R.string.article_title),
            stringResource(R.string.article_intro),
            stringResource(R.string.article_content)
        )
    }
}