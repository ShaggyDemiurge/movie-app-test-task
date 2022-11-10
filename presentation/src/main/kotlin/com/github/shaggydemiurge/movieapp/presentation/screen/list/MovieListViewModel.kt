package com.github.shaggydemiurge.movieapp.presentation.screen.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.shaggydemiurge.movieapp.core.usecase.LoadMovieListPage
import com.github.shaggydemiurge.movieapp.presentation.common.ext.request
import com.github.shaggydemiurge.movieapp.presentation.common.paginator.PagePaginator
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

class MovieListViewModel(
    private val loadMovieListPage: LoadMovieListPage,
) : ViewModel() {

    private var paginator by mutableStateOf(
        PagePaginator(1) { page ->
            loadMovieListPage.load(page)
        }
    )

    val movieList get() = paginator.data

    var listLoading by mutableStateOf(false)
        private set

    private val errorRelay =
        MutableSharedFlow<Throwable>(extraBufferCapacity = 10, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    val errors get() = errorRelay

    fun loadMore() {
        if (listLoading) return
        request(
            onLoading = this::listLoading::set,
            onError = errorRelay::tryEmit,
            showLoadingDelay = 100L
        ) {
            paginator = paginator.requestMore()
        }
    }

    companion object {
        val module = module {
            viewModelOf(::MovieListViewModel)
        }
    }
}
