package com.github.shaggydemiurge.movieapp.presentation.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface Screen : Parcelable {
    @Parcelize
    object List : Screen

    @Parcelize
    data class Details(val movieId: String) : Screen
}
