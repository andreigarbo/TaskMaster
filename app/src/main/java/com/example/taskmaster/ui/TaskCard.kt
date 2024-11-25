package com.example.taskmaster.ui
import android.os.CountDownTimer
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmaster.loadDifficultyMapFromAssets
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun TaskCard(taskCardViewModel: TaskCardViewModel = viewModel()){
    var isExpanded by remember { mutableStateOf(false) }
    val deadline = "20241126080000"
    var millisInFuture: Long = getMillisUntilDeadline(deadline)
    val timeData = remember {
        mutableLongStateOf(millisInFuture)
    }
    val difficultyLevel = 2
    val tags = listOf("tag1", "tag2")
    val name = "Task name"
    val durationMinutes = 5
    var selectedEmoji by remember { mutableStateOf("\uD83E\uDEE0") }
    val difficultyMap = loadDifficultyMapFromAssets()
    val difficultyText = difficultyMap[difficultyLevel.toString()] ?: "Unknown"
    var dropDownExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(8.dp),
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
                .clickable { isExpanded = !isExpanded }
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
                    text = name,
                    modifier = Modifier
                        .padding(16.dp),
                    //textAlign = TextAlign.Center
                )
                Text(
                    text = "($durationMinutes min)",
                    modifier = Modifier
                        .padding(16.dp),
                    //textAlign = TextAlign.Center
                )
                Text(
                    text = selectedEmoji,
                    modifier = Modifier
                        .padding(16.dp),
                    //textAlign = TextAlign.Center
                )
                if (!isExpanded) {
                    Text(
                        text = difficultyText,
                        modifier = Modifier
                            .padding(16.dp),
                        //textAlign = TextAlign.Center
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.TopEnd)
                    ) {
                        IconButton(
                            onClick = { dropDownExpanded = !dropDownExpanded },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "Localized description"
                            )
                        }
                        DropdownMenu(
                            expanded = dropDownExpanded,
                            onDismissRequest = { dropDownExpanded = false },
                        ) {
                            DropdownMenuItem(
                                onClick = {},
                                text = { Text("Edit Task") }
                            )
                            DropdownMenuItem(
                                onClick = {},
                                text = { Text("Start Task") }
                            )
                            DropdownMenuItem(
                                onClick = {},
                                text = { Text("Mark Task as Done") }
                            )
                            DropdownMenuItem(
                                onClick = {},
                                text = { Text("Delete Task") }
                            )
                        }
                    }
                }
            }
            if (isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    val countDownTimer =
                        object : CountDownTimer(millisInFuture, 1000) {
                            override fun onTick(millisUntilFinished: Long) {
                                timeData.longValue = millisUntilFinished
                            }
                            override fun onFinish() {

                            }
                    }
                    DisposableEffect(key1 = "key") {
                        countDownTimer.start()
                        onDispose {
                            countDownTimer.cancel()
                        }
                    }
                    Text(text = "Deadline: " + formatTime(timeData.longValue))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Tags: $tags")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Difficulty: $difficultyText")
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

fun getMillisUntilDeadline(deadline: String): Long {
    val dateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
    val deadlineDate = dateFormat.parse(deadline)
    val currentDate = Calendar.getInstance().time
    return if (deadlineDate != null) {
        deadlineDate.time - currentDate.time
    } else {
        0L
    }
}

fun formatTime(millis: Long): String {
    val seconds = (millis / 1000) % 60
    val minutes = (millis / (1000 * 60)) % 60
    val hours = (millis / (1000 * 60 * 60)) % 24
    val days = millis / (1000 * 60 * 60 * 24)
    var finalString = ""
    if (days.toInt() != 0) {
        finalString += "%d days, ".format(days)
        //return "%d days, %02d hours, %02d minutes".format(days, hours, minutes)
    }
    if (hours.toInt() != 0) {
        finalString += "%02d hours, ".format(hours)
    }
    finalString += "%02d minutes".format(minutes)
    return finalString
}

@Composable
fun EmojiItem(emoji: String, onClick: () -> Unit) {
    Text(
        text = emoji,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
            .padding(16.dp),
        fontSize = 32.sp,
        color = Color.Black
    )
}