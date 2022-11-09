package com.github.shaggydemiurge.movieapp.presentation.screen.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.github.shaggydemiurge.movieapp.core.entities.MovieSummary
import com.github.shaggydemiurge.movieapp.core.util.DateTimeFormat
import com.github.shaggydemiurge.movieapp.presentation.common.LoadMoreHandler
import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieListView(
    modifier: Modifier = Modifier,
    viewModel: MovieListViewModel = koinViewModel(),
    onMovieSelect: (movieId: String) -> Unit,
) {
    val lazyListState = rememberLazyListState()

    val movieList by viewModel.movieList
    val listLoading by viewModel.listLoading

    LoadMoreHandler(lazyListState, buffer = 4) {
        viewModel.loadMore()
    }

    LazyColumn(
        modifier = modifier,
        state = lazyListState
    ) {
        items(movieList, key = { it.id }) { item ->
            MovieCard(
                movieSummary = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .defaultMinSize(minHeight = 200.dp).clickable { onMovieSelect(item.id) }
            )
        }
        if (listLoading) {
            item {
                Loader(modifier = Modifier.fillMaxWidth().height(40.dp))
            }
        }
    }
}

@Composable
fun MovieCard(movieSummary: MovieSummary, modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier) {
        val posterGuideline = createGuidelineFromStart(0.3f)
        val (poster, title, releaseDate, ratingIndicator, ratingBackground, ratingValue) = createRefs()

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

        val score = movieSummary.avgScore
        if (score != null) {
            val bgColor = MaterialTheme.colors.background
            Canvas(
                modifier = Modifier.constrainAs(ratingBackground) {
                    top.linkTo(ratingIndicator.top, 2.dp)
                    bottom.linkTo(ratingIndicator.bottom, 2.dp)
                    start.linkTo(ratingIndicator.start, 2.dp)
                    end.linkTo(ratingIndicator.end, 2.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
            ) {
                drawCircle(color = bgColor)
            }

            CircularProgressIndicator(
                progress = score / 100f,
                modifier = Modifier
                    .size(48.dp)
                    .constrainAs(ratingIndicator) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(posterGuideline)
                        end.linkTo(posterGuideline)
                    }
            )
            Text(
                text = score.toInt().toString(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(ratingValue) {
                    top.linkTo(ratingIndicator.top)
                    bottom.linkTo(ratingIndicator.bottom)
                    start.linkTo(ratingIndicator.start)
                    end.linkTo(ratingIndicator.end)
                }
            )
        }

        createVerticalChain(title, releaseDate, chainStyle = ChainStyle.Packed)

        Text(
            movieSummary.title,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(posterGuideline, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(releaseDate.top, 8.dp)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            movieSummary.releaseDate.format(DateTimeFormat.SLASH_DMY),
            textAlign = TextAlign.End,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                .constrainAs(releaseDate) {
                    start.linkTo(posterGuideline, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    top.linkTo(title.bottom)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
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
