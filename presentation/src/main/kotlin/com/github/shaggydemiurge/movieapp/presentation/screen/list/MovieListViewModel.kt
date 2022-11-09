package com.github.shaggydemiurge.movieapp.presentation.screen.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.github.shaggydemiurge.movieapp.core.entities.MovieSummary
import com.github.shaggydemiurge.movieapp.presentation.common.PagePaginator
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

class MovieListViewModel : ViewModel() {

    private val paginator = PagePaginator<MovieSummary> { page ->
        TODO()
    }

    val movieList
        @Composable
        get() = paginator.subscribeToCurrentData().collectAsState(emptyList())

    val listLoading
        @Composable
        get() = paginator.subscribeToLoading().collectAsState(false)

    fun loadMore() {
        if (paginator.isLoading || paginator.reachedEnd) return
    }

    companion object {
        val module = module {
            viewModelOf(::MovieListViewModel)
        }
    }
}
