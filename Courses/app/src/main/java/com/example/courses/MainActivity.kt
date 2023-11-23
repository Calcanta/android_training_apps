package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.data.DataSource
import com.example.courses.data.Topic
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CourseApp()
        }
    }
}

@Composable
fun CourseApp() {
    CoursesTheme {
        CourseGrid(topicList = DataSource().loadTopics())
    }
}

@Composable
fun CourseCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(68.dp),
        elevation = 4.dp
    ) {
        Row {
            Image(
                painter = painterResource(id = topic.imageId),
                contentDescription = stringResource(id = topic.topicNameId),
                modifier = Modifier
                    .width(68.dp)
                    .height(68.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(id = topic.topicNameId),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .align(Alignment.Start)
                )
                Row {
                    Image(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = stringResource(R.string.grain_icon),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = topic.students.toString(),
                        style = MaterialTheme.typography.caption,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun CourseGrid(topicList: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
    ) {
        items(topicList) { topic ->
            CourseCard(topic = topic)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseGridPreview() {
    CoursesTheme {
        CourseCard(topic = Topic(R.string.music, 212, R.drawable.music))
    }
}