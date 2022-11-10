package com.github.shaggydemiurge.movieapp.presentation.screen.details

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.shaggydemiurge.movieapp.core.entities.MovieDetails
import com.github.shaggydemiurge.movieapp.presentation.common.widget.LoadableView
import com.github.shaggydemiurge.movieapp.presentation.common.widget.Logged
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MovieDetailsView(
    movieId: Int,
    modifier: Modifier = Modifier,
    viewModel: MovieDetailsViewModel = koinViewModel(parameters = { parametersOf(MovieId(movieId)) }),
    onBack: () -> Unit,
) {
    Logged("MovieDetailsView") {
        LoadableView(viewModel.movieDetails, modifier) { details, m ->
            MovieDetailsContent(movieDetails = details, modifier = m, onBack = onBack)
        }
    }
}

@Composable
fun MovieDetailsContent(
    movieDetails: MovieDetails,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
) {
    Text(movieDetails.toString(), modifier = modifier)
}
