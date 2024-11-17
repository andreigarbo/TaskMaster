package com.example.taskmaster.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(homeScreenViewModel: HomeScreenViewModel = viewModel()) {
    Scaffold (
        topBar = { TopAppBar() },
        bottomBar = { BottomAppBar() }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(innerPadding)
        ) {
            TaskCardContainer(
                modifier = Modifier
                    .fillMaxHeight()
            )
        }
    }
}