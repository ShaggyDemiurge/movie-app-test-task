package com.github.shaggydemiurge.movieapp.presentation.screen.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.shaggydemiurge.movieapp.presentation.common.LoadMoreHandler
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieListView(
    modifier: Modifier = Modifier,
    viewModel: MovieListViewModel = koinViewModel(),
    onMovieSelect: (movieId: String) -> Unit,
) {
    val lazyListState = rememberLazyListState()

    LoadMoreHandler(lazyListState, buffer = 4) {
        viewModel.loadMore()
    }

    LazyColumn(
        modifier = modifier,
        state = lazyListState
    ) {
    }
}
