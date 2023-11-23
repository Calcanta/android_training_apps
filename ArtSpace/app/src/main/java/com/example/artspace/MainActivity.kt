package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val useCommonSize = windowSizeClass.widthSizeClass <= WindowWidthSizeClass.Medium
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    ArtSpace(useCommonSize)
                }
            }
        }
    }
}

@Composable
fun ArtSpace(useCommonSize: Boolean) {
    var pieceNumber by remember {
        mutableStateOf(1)
    }
    val pieceInfo = setPieceData(pieceNumber = pieceNumber)
    val buttonModifier = if (useCommonSize) {
        Modifier
            .padding(start = 5.dp, end = 35.dp)
            .size(120.dp, 40.dp)
    } else {
        Modifier
            .padding(start = 5.dp, end = 35.dp)
            .size(200.dp, 70.dp)
    }
    val buttonFontSize = if (useCommonSize) 20.sp else 32.sp

    Column(
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SetImageCard(pieceInfo[0], pieceInfo[1], useCommonSize)
        Spacer(modifier = Modifier.height(16.dp))
        SetDescriptionCard(pieceInfo[1], pieceInfo[2], pieceInfo[3], useCommonSize)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            Modifier
                .padding(start = 10.dp, end = 10.dp, bottom = 15.dp)
        ) {
            Button(
                onClick = {
                    pieceNumber = getPreviousNumber(pieceNumber)
                },
                shape = RoundedCornerShape(20.dp),
                modifier = buttonModifier
            ) {
                Text(text = stringResource(id = R.string.previous), fontSize = buttonFontSize)
            }
            Button(
                onClick = {
                    pieceNumber = getNextNumber(pieceNumber)
                },
                shape = RoundedCornerShape(20.dp),
                modifier = buttonModifier
            ) {
                Text(text = stringResource(id = R.string.Next), fontSize = buttonFontSize)
            }
        }
    }
}

@Composable
fun SetImageCard(
    imageId: Int,
    imageDescriptionId: Int,
    useCommonSize: Boolean
) {
    val sizeModifier = if (useCommonSize) {
        Modifier
            .padding(10.dp)
            .size(400.dp, 300.dp)
            .fillMaxSize()
    } else {
        Modifier
            .padding(10.dp)
            .size(800.dp, 400.dp)
            .fillMaxSize()
    }

    Card(border = BorderStroke(2.dp, Color.Gray)) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = stringResource(id = imageDescriptionId),
            modifier = sizeModifier
        )
    }
}

@Composable
fun SetDescriptionCard(
    pieceNameId: Int,
    authorNameId: Int,
    yearId: Int,
    useCommonSize: Boolean
) {
    val sizeModifier = if (useCommonSize) {
        Modifier
            .padding(10.dp)
            .size(400.dp, 80.dp)
            .fillMaxSize()
    } else {
        Modifier
            .padding(10.dp)
            .size(800.dp, 150.dp)
            .fillMaxSize()
    }

    val smallFontSize = if (useCommonSize) 16.sp else 26.sp
    val bigFontSize = if (useCommonSize) 20.sp else 32.sp

    Card(
        elevation = 25.dp,
        contentColor = Color.DarkGray,
        modifier = sizeModifier,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Text(
                text = stringResource(id = pieceNameId),
                fontSize = bigFontSize,
                textAlign = TextAlign.Center
            )
            Row {
                Text(
                    text = stringResource(id = authorNameId),
                    fontSize = smallFontSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = " (${stringResource(id = yearId)})",
                    fontSize = smallFontSize,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun setPieceData(pieceNumber: Int): List<Int> {
    val pieceInfo = when (pieceNumber) {
        1 -> listOf(
            R.drawable.fire_in_the_eyes,
            R.string.fire_in_the_eyes,
            R.string.josh_t,
            R.string.y20
        )
        2 -> listOf(
            R.drawable.focus,
            R.string.focus,
            R.string.ari_s,
            R.string.y21
        )
        3 -> listOf(
            R.drawable.green,
            R.string.green,
            R.string.janna_a,
            R.string.y22
        )
        4 -> listOf(
            R.drawable.innocent_creature,
            R.string.innocent_creature,
            R.string.andre_g,
            R.string.y22
        )
        5 -> listOf(
            R.drawable.josh_and_his_friends,
            R.string.josh_and_his_friends,
            R.string.leah_m,
            R.string.y23
        )
        6 -> listOf(
            R.drawable.motherhood,
            R.string.motherhood,
            R.string.sally_s,
            R.string.y20
        )
        7 -> listOf(
            R.drawable.orange,
            R.string.orange,
            R.string.charles_z,
            R.string.y22
        )
        8 -> listOf(
            R.drawable.ping,
            R.string.lonely_walk,
            R.string.mica_b,
            R.string.y21
        )
        9 -> listOf(
            R.drawable.please_do_not_disturb,
            R.string.please_do_not_disturb,
            R.string.rachel_j,
            R.string.y20
        )
        10 -> listOf(
            R.drawable.quiet_family_afternoon,
            R.string.quiet_family_afternoon,
            R.string.vivian_a,
            R.string.y20
        )
        11 -> listOf(
            R.drawable.sadness_in_nature,
            R.string.sadness_in_nature,
            R.string.marc_m,
            R.string.y23
        )
        12 -> listOf(
            R.drawable.strength,
            R.string.strength,
            R.string.gabriel_w,
            R.string.y21
        )
        13 -> listOf(
            R.drawable.sweetness_in_the_cup,
            R.string.sweetness_in_the_cup,
            R.string.luis_r,
            R.string.y22
        )
        else -> listOf(
            R.drawable.thinking_pug,
            R.string.thinking_pug,
            R.string.ruth_c,
            R.string.y22
        )
    }
    return pieceInfo
}

fun getPreviousNumber(number: Int): Int {
    val newNumber = when (number) {
        2 -> 1
        3 -> 2
        4 -> 3
        5 -> 4
        6 -> 5
        7 -> 6
        8 -> 7
        9 -> 8
        10 -> 9
        11 -> 10
        12 -> 11
        13 -> 12
        14 -> 13
        else -> 14
    }
    return newNumber
}

fun getNextNumber(number: Int): Int {
    val newNumber = when (number) {
        1 -> 2
        2 -> 3
        3 -> 4
        4 -> 5
        5 -> 6
        6 -> 7
        7 -> 8
        8 -> 9
        9 -> 10
        10 -> 11
        11 -> 12
        12 -> 13
        13 -> 14
        else -> 1
    }
    return newNumber
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpace(true)
    }
}