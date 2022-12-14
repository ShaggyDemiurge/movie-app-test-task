package com.github.shaggydemiurge.movieapp.presentation.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import com.github.shaggydemiurge.movieapp.core.entities.MovieSummary
import com.github.shaggydemiurge.movieapp.core.util.DateTimeFormat
import com.github.shaggydemiurge.movieapp.presentation.R
import com.github.shaggydemiurge.movieapp.presentation.common.SnackbarErrorHandler
import com.github.shaggydemiurge.movieapp.presentation.common.ext.shiftTo
import com.github.shaggydemiurge.movieapp.presentation.common.widget.LoadMoreHandler
import com.github.shaggydemiurge.movieapp.presentation.common.widget.Logged
import com.github.shaggydemiurge.movieapp.presentation.common.widget.ScoreView
import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieListView(
    modifier: Modifier = Modifier,
    viewModel: MovieListViewModel = koinViewModel(),
    onMovieSelect: (movieId: Int) -> Unit,
) {
    Logged("MovieListView") {
        val lazyListState = rememberLazyListState()

        LoadMoreHandler(lazyListState, buffer = 4) {
            viewModel.loadMore()
        }

        Box(modifier = modifier) {
            SnackbarErrorHandler(
                errorFlow = viewModel.errors,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
            )

            val cardBgColorNormal = MaterialTheme.colors.background
            val cardBgColorAlternate = cardBgColorNormal.shiftTo(0.2f, Color.Gray)

            val refreshState = rememberPullRefreshState(
                refreshing = viewModel.listRefreshing,
                onRefresh = { viewModel.refresh() }
            )

            LazyColumn(
                modifier = Modifier
                    .matchParentSize()
                    .pullRefresh(refreshState),
                state = lazyListState
            ) {
                if (refreshState.progress > 0) {
                    item {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height((100 * refreshState.progress.coerceAtMost(1f)).toInt().dp)
                                .background(cardBgColorAlternate)

                        ) {
                            Text(
                                stringResource(R.string.pull_to_refresh),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
                itemsIndexed(viewModel.movieList, key = { _, item -> item.id }) { index, item ->
                    MovieCard(
                        movieSummary = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 200.dp)
                            .clickable { onMovieSelect(item.id) }
                            .background(if (index % 2 == 0) cardBgColorNormal else cardBgColorAlternate)
                            .padding(8.dp)
                    )
                }
                if (viewModel.listLoading) {
                    item {
                        Loader(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .background(
                                    if (viewModel.movieList.size % 2 == 0) cardBgColorNormal else cardBgColorAlternate
                                )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MovieCard(movieSummary: MovieSummary, modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier) {
        val posterGuideline = createGuidelineFromStart(0.3f)
        val (poster, placeholder, title, releaseDate, scoreIndicator) = createRefs()

        Icon(
            painterResource(R.drawable.filmstrip),
            null,
            modifier = Modifier.constrainAs(placeholder) {
                start.linkTo(poster.start)
                top.linkTo(poster.top)
                bottom.linkTo(poster.bottom)
                end.linkTo(poster.end)
            }.zIndex(-1f)
                .size(64.dp)
        )

        GlideImage(
            imageModel = { movieSummary.posterUri },
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
            score = movieSummary.avgScore ?: 0f,
            modifier = Modifier
                .size(48.dp)
                .shadow(4.dp, shape = CircleShape)
                .constrainAs(scoreIndicator) {
                    bottom.linkTo(parent.bottom, 8.dp)
                    start.linkTo(posterGuideline)
                    end.linkTo(posterGuideline)
                    visibility = if (movieSummary.avgScore != null) Visibility.Visible else Visibility.Gone
                }
        )

        createVerticalChain(title, releaseDate, chainStyle = ChainStyle.Packed)

        Text(
            movieSummary.title,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h6,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(posterGuideline, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(releaseDate.top, 16.dp)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            movieSummary.releaseDate?.format(DateTimeFormat.SLASH_DMY) ?: "",
            textAlign = TextAlign.End,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .constrainAs(releaseDate) {
                    start.linkTo(posterGuideline, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    top.linkTo(title.bottom)
                    bottom.linkTo(parent.bottom, 8.dp)
                    width = Dimension.fillToConstraints
                    visibility = if (movieSummary.releaseDate != null) Visibility.Visible else Visibility.Gone
                }
        )
    }
}

@Composable
fun Loader(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
