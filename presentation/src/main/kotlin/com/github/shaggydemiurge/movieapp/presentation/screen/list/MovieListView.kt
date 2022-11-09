package com.github.shaggydemiurge.movieapp.presentation.screen.list

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.shaggydemiurge.movieapp.core.entities.MovieSummary
import com.github.shaggydemiurge.movieapp.presentation.common.LoadMoreHandler
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
        val itemModifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .defaultMinSize(minHeight = 100.dp)
        items(movieList, key = { it.id }) { item ->
            MovieCard(movieSummary = item, modifier = itemModifier, onClick = { onMovieSelect(item.id) })
        }
        if (listLoading) {
            Loader(modifier = itemModifier)
        }
    }
}

@Composable
fun MovieCard(movieSummary: MovieSummary, modifier: Modifier = Modifier, onClick: (MovieSummary) -> Unit) {
    // TODO
}

fun Loader(modifier: Modifier = Modifier) {
    // TODO
}
