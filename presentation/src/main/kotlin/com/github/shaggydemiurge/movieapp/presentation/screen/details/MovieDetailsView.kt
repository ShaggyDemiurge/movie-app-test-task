package com.github.shaggydemiurge.movieapp.presentation.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import com.bumptech.glide.request.RequestOptions
import com.github.shaggydemiurge.movieapp.core.entities.MovieDetails
import com.github.shaggydemiurge.movieapp.core.util.DateTimeFormat
import com.github.shaggydemiurge.movieapp.presentation.R
import com.github.shaggydemiurge.movieapp.presentation.common.entity.Loadable
import com.github.shaggydemiurge.movieapp.presentation.common.widget.LoadableView
import com.github.shaggydemiurge.movieapp.presentation.common.widget.Logged
import com.github.shaggydemiurge.movieapp.presentation.common.widget.ScoreView
import com.skydoves.landscapist.glide.GlideImage
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
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 16.dp, top = 16.dp)
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
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colors.onPrimary) {
            MovieDetailsCard(
                movieDetails,
                Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .shadow(8.dp)
                    .background(MaterialTheme.colors.primary),
                onBack
            )
        }
        movieDetails.overview?.let { overview ->
            Text(text = overview, modifier = Modifier.padding(16.dp).fillMaxWidth())
        }
    }
}

@Composable
fun MovieDetailsCard(
    movieDetails: MovieDetails,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
) {
    ConstraintLayout(modifier = modifier) {
        val posterGuideline = createGuidelineFromStart(0.4f)
        val (poster, title, tagline, releaseDate, scoreIndicator, backButton) = createRefs()

        GlideImage(
            imageModel = { movieDetails.posterUri },
            previewPlaceholder = R.drawable.filmstrip,
            requestOptions = {
                RequestOptions.placeholderOf(R.drawable.filmstrip)
                    .error(R.drawable.filmstrip)
                    .fallback(R.drawable.filmstrip)
            },
            modifier = Modifier.constrainAs(poster) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(posterGuideline)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        )
        ScoreView(
            movieDetails.avgScore ?: 0f,
            trackColor = MaterialTheme.colors.secondary,
            modifier = Modifier
                .size(48.dp)
                .shadow(8.dp, shape = CircleShape)
                .constrainAs(scoreIndicator) {
                    bottom.linkTo(parent.bottom, 8.dp)
                    end.linkTo(parent.end, 8.dp)
                    visibility = if (movieDetails.avgScore != null) Visibility.Visible else Visibility.Gone
                }
        )

        IconButton(
            onClick = onBack,
            modifier = Modifier.constrainAs(backButton) {
                end.linkTo(parent.end, 16.dp)
                top.linkTo(parent.top, 16.dp)
            }
        ) {
            Icon(
                Icons.Default.ArrowBack,
                null,
                modifier = Modifier.size(24.dp)
            )
        }

        Text(
            movieDetails.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(poster.end, 8.dp)
                    end.linkTo(parent.end, 8.dp)
                    top.linkTo(backButton.bottom, 4.dp)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            movieDetails.tagline ?: "",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .constrainAs(tagline) {
                    start.linkTo(poster.end, 8.dp)
                    end.linkTo(parent.end, 8.dp)
                    linkTo(
                        title.bottom,
                        scoreIndicator.top,
                        topMargin = 16.dp,
                        bias = 0f
                    )
                    width = Dimension.fillToConstraints
                    visibility = if (movieDetails.tagline == null) Visibility.Gone else Visibility.Visible
                }
        )

        Text(
            movieDetails.releaseDate?.format(DateTimeFormat.SLASH_DMY) ?: "",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .constrainAs(releaseDate) {
                    start.linkTo(poster.end, 8.dp)
                    end.linkTo(scoreIndicator.start)
                    bottom.linkTo(parent.bottom, 8.dp)
                    width = Dimension.fillToConstraints
                    visibility = if (movieDetails.releaseDate != null) Visibility.Visible else Visibility.Gone
                }
        )
    }
}
