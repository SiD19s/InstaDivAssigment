package com.example.instadivassigment.Screens

import android.graphics.fonts.FontStyle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.instadivassigment.R
import kotlinx.coroutines.delay

@Composable
fun NextScreen(selectedTag: String) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(selectedTag) {
        // Delay for a brief moment to allow fade animation to be visible
        delay(500) // Adjust the delay time as needed
        isVisible = selectedTag.isNotEmpty()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.screenBgColor)),
        contentAlignment = Alignment.Center,
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = selectedTag,
                fontSize = 70.sp
            )
        }
    }
}