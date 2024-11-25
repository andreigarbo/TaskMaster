package com.example.taskmaster

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.gson.Gson
import java.io.InputStreamReader
import java.nio.charset.Charset

// Function to load JSON from assets
@Composable
fun loadDifficultyMapFromAssets(): Map<String, String> {
    val context = LocalContext.current
    val jsonString = context.assets.open("DifficultyList.json").use { inputStream ->
        InputStreamReader(inputStream, Charset.forName("UTF-8")).readText()
    }

    // Use Gson to parse the JSON into a Map
    val gson = Gson()
    return gson.fromJson(jsonString, Map::class.java) as Map<String, String>
}

