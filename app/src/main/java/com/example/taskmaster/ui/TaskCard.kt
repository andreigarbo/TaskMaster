package com.example.taskmaster.ui
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TaskCard(taskCardViewModel: TaskCardViewModel = viewModel()){
    Column(
        modifier = Modifier
            .padding(8.dp)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedCard (
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                //.size(width = 240.dp, height = 100.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(Color.Cyan)
                )
                Text(
                    text = "Task name",
                    modifier = Modifier
                        .padding(16.dp),
                    //textAlign = TextAlign.Center
                )
                Text(
                    text = "(5 min)",
                    modifier = Modifier
                        .padding(16.dp),
                    //textAlign = TextAlign.Center
                )
                Text(
                    text = "C/E",
                    modifier = Modifier
                        .padding(16.dp),
                    //textAlign = TextAlign.Center
                )
                Text(
                    text = "Dif",
                    modifier = Modifier
                        .padding(16.dp),
                    //textAlign = TextAlign.Center
                )
            }
        }
    }
}