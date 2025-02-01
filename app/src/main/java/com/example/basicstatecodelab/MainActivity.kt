package com.example.basicstatecodelab
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicstatecodelab.MainActivity.Companion.TAG
import com.example.basicstatecodelab.ui.theme.BasicStateCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicStateCodelabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        WellnessScreen()
                    }
                }
            }
        }
    }

    companion object {
        const val TAG = "SW"
    }
}

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    var count by remember { mutableStateOf(0)}
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(count > 0) {
            var showTask by remember { mutableStateOf(true) }
            if (showTask) {
                WellnessTaskItem(
                    taskName = "Have you taken your 15 minute walk today?",
                    onClose = { showTask = false }
                )
            }

            Text(
                text = "You've had ${count} glasses.",
                modifier = modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(16.dp)
        ) {
            Button(
                onClick = {
                    count++
                    Log.d(TAG, "WaterCounter: onClick")
                },
                modifier = modifier.padding(end = 8.dp),
            ) {
                Text(
                    text = "Add One",
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Button(
                onClick = {
                    count = 0
                    Log.d(TAG, "Clear Water Count: onClick")
                },
                modifier = modifier.padding(start = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            ) {
                Text(
                    text = "Clear Water Count",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    WaterCounter(modifier)
}

@Preview(showBackground = true)
@Composable
fun WaterCounterPreview() {
    BasicStateCodelabTheme {
        WaterCounter()
    }
}