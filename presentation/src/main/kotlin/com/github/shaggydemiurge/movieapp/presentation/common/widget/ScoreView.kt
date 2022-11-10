package com.github.shaggydemiurge.movieapp.presentation.common.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ScoreView(score: Float, modifier: Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        val bgColor = MaterialTheme.colors.background
        Canvas(
            modifier = Modifier.matchParentSize()
        ) {
            drawCircle(color = bgColor)
        }

        CircularProgressIndicator(
            progress = score / 10f,
            modifier = Modifier
                .matchParentSize(),
            strokeWidth = 6.dp
        )
        Text(
            text = String.format("%.1f", score),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold
        )
    }
}
