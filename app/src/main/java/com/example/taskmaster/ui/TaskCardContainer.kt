package com.example.taskmaster.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TaskCardContainer(taskCardContainerViewModel: TaskCardContainerViewModel = viewModel(), modifier: Modifier) {
    Column (
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TaskCard()
        TaskCard()
        TaskCard()
        TaskCard()
        TaskCard()
        TaskCard()
        TaskCard()
        TaskCard()
        TaskCard()
        TaskCard()
    }
}