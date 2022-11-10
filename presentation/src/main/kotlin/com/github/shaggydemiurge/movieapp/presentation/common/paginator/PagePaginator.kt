package com.github.shaggydemiurge.movieapp.presentation.common.paginator

import com.github.shaggydemiurge.movieapp.core.entities.PagedResult

object PagePaginator {

    operator fun <Model> invoke(startPage: Int = 0, onLoadMore: suspend (page: Int) -> PagedResult<Model>) =
        ImmutablePaginator<Model, State<Model>>(
            state = State(emptyList(), startPage - 1, null),
            onLoadMore = { oldState ->
                val result = onLoadMore(oldState.currentLoadedPage + 1)
                if (result.data.isNotEmpty()) {
                    State(
                        oldState.data + result.data,
                        result.page,
                        result.maxPages
                    )
                } else {
                    oldState
                }
            },
            onReplaceData = { state, newData -> state.copy(data = newData) },
            isRequestRequired = { state ->
                state.maxPages == null || state.currentLoadedPage < state.maxPages
            }
        )

    data class State<Model>(override val data: List<Model>, val currentLoadedPage: Int, val maxPages: Int?) :
        ImmutablePaginator.PaginationState<Model>
}
