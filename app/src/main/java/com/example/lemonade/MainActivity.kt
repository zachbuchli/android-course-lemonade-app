package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}


@Composable
fun LemonadeApp() {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        LemonadeImageAndText(modifier = Modifier
            .wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun LemonadeImageAndText(modifier: Modifier = Modifier) {

    var cardState by remember { mutableStateOf(1) }
    var squezeCount by remember { mutableStateOf(0)}
    var squezeCountMax by remember { mutableStateOf(2) }


    val buttonImagePainterResource = when(cardState) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val labelStringResource = when(cardState) {
        1 -> R.string.tree_label_text
        2 -> R.string.lemon_label_text
        3 -> R.string.glass_full_label_text
        else -> R.string.glass_empty_label_text
    }

    val buttonImageContentDesc = when(cardState) {
        1 -> R.string.tree_desc
        2 -> R.string.lemon_desc
        3 -> R.string.glass_full_desc
        else -> R.string.empty_glass_desc
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            when(cardState){
                1 -> {
                    cardState++
                    squezeCountMax = (2..4).random()
                }
                2 -> {
                    when(squezeCount){
                        in (0..(squezeCountMax - 2)) -> squezeCount++
                        else -> {
                            squezeCount = 0
                            cardState++
                        }
                    }
                }
                3 -> cardState++
                4 -> cardState = 1
            }
        }) {
            Image(painter = painterResource(
                id = buttonImagePainterResource),
                contentDescription = stringResource(id = buttonImageContentDesc),
                modifier = Modifier
                    .wrapContentSize()
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(id = labelStringResource),
            fontSize = 18.sp)
    }
}
