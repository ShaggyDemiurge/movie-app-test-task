package com.github.shaggydemiurge.movieapp.presentation.common.ext

import androidx.compose.ui.graphics.Color

fun Color.shiftTo(percent: Float, target: Color): Color =
    Color(
        this.red * (1 - percent) + target.red * percent,
        this.green * (1 - percent) + target.green * percent,
        this.blue * (1 - percent) + target.blue * percent,
        this.alpha * (1 - percent) + target.alpha * percent
    )
