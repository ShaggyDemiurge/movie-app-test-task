package com.github.shaggydemiurge.movieapp.presentation.screen.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.shaggydemiurge.movieapp.core.entities.MovieDetails
import com.github.shaggydemiurge.movieapp.presentation.common.entity.Loadable
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
        Box(modifier = modifier) {
            LoadableView(viewModel.movieDetails, modifier = Modifier.matchParentSize()) { details, m ->
                MovieDetailsContent(movieDetails = details, modifier = m, onBack = onBack)
            }
            if (viewModel.movieDetails is Loadable.Error) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.align(Alignment.TopEnd).padding(end = 16.dp, top = 16.dp)
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        null,
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
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
