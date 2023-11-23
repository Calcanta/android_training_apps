package com.example.taskmanager

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanager.ui.theme.TaskManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AllTaskCompletedDisplay(
                        title = stringResource(R.string.all_task_title),
                        congrats_message = stringResource(R.string.nice_work_text)
                    )
                }
            }
        }
    }
}

@Composable
fun AllTaskCompletedDisplay(title: String, congrats_message: String) {
    val image = painterResource(id = R.drawable.ic_task_completed)
    Column (
        modifier = Modifier
            .wrapContentHeight(align = Alignment.CenterVertically)
            .wrapContentWidth(align = Alignment.CenterHorizontally)
    ){
        Image(
            painter = image,
            contentDescription = null
        )
        AllTaskCompletedText(title, congrats_message)
    }
}

@Composable
fun AllTaskCompletedText(title: String, congrats_message: String) {
    Column {
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp, bottom = 8.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = congrats_message,
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TaskManagerTheme {
        AllTaskCompletedDisplay(
            title = stringResource(R.string.all_task_title),
            congrats_message = stringResource(R.string.nice_work_text)
        )
    }
}