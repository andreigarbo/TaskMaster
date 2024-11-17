package com.example.taskmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmaster.ui.HomeScreen
import com.example.taskmaster.ui.theme.TaskMasterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskMasterTheme {
                HomeScreen(viewModel())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskMasterPreview() {
    TaskMasterTheme {
        HomeScreen(viewModel())
    }
}