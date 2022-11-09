package com.github.shaggydemiurge.movieapp.presentation.screen.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.shaggydemiurge.movieapp.core.usecase.LoadMovieListPage
import com.github.shaggydemiurge.movieapp.presentation.common.PagePaginator
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

class MovieListViewModel(
    private val loadMovieListPage: LoadMovieListPage,
) : ViewModel() {

    private val paginator = PagePaginator(1) { page ->
        loadMovieListPage.load(page)
    }

    val movieList
        @Composable
        get() = paginator.subscribeToCurrentData().collectAsState(emptyList())

    val listLoading
        @Composable
        get() = paginator.subscribeToLoading().collectAsState(false)

    val listErrors get() = paginator.subscribeToErrors()

    fun loadMore() {
        if (paginator.isLoading || paginator.reachedEnd) return
        viewModelScope.launch {
            paginator.requestMore()
        }
    }

    companion object {
        val module = module {
            viewModelOf(::MovieListViewModel)
        }
    }
}
