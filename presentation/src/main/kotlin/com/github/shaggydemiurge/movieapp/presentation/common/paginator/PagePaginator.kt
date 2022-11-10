package com.github.shaggydemiurge.movieapp.presentation.common.paginator

import com.github.shaggydemiurge.movieapp.core.entities.PagedResult

object PagePaginator {

    operator fun <Model> invoke(startPage: Int = 0, onLoadMore: suspend (page: Int) -> PagedResult<Model>) =
        ImmutablePaginator(
            state = State(emptyList(), startPage - 1, null),
            delegate = Delegate(onLoadMore)
        )

    class Delegate<Model>(private val onLoadMore: suspend (page: Int) -> PagedResult<Model>) :
        ImmutablePaginator.Delegate<Model, State<Model>> {
        override suspend fun State<Model>.loadMore(): State<Model> {
            val result = onLoadMore(currentLoadedPage + 1)
            return if (result.data.isNotEmpty()) {
                State(
                    data + result.data,
                    result.page,
                    result.maxPages
                )
            } else {
                this
            }
        }

        override fun State<Model>.replaceData(newData: List<Model>): State<Model> = copy(
            data = newData
        )

        override fun State<Model>.isRequestRequired(): Boolean =
            maxPages == null || currentLoadedPage < maxPages
    }

    data class State<Model>(override val data: List<Model>, val currentLoadedPage: Int, val maxPages: Int?) :
        ImmutablePaginator.PaginationState<Model>
}
