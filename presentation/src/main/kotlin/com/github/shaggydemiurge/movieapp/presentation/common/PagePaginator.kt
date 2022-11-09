package com.github.shaggydemiurge.movieapp.presentation.common

import com.github.shaggydemiurge.movieapp.core.entities.PagedResult

class PagePaginator<Model>(
    private val startingPage: Int = 0,
    private val dataProvider: suspend (page: Int) -> PagedResult<Model>,
) :
    Paginator<Model, PagePaginator.State<Model>>() {

    val reachedEnd get() = state.reachedLastPage

    override fun replaceDataset(state: State<Model>, newDataset: List<Model>): State<Model> =
        state.copy(data = newDataset)

    override suspend fun updateStateWithNewData(state: State<Model>): State<Model> {
        val pagedResult = dataProvider(state.currentLoadedPage + 1)
        return State(
            state.data + pagedResult.data,
            if (pagedResult.data.isEmpty()) state.currentLoadedPage else pagedResult.page,
            pagedResult.page >= pagedResult.maxPages
        )
    }

    override fun createEmptyState(): State<Model> = State(emptyList(), startingPage - 1)

    override fun requestRequired(state: State<Model>): Boolean = !state.reachedLastPage

    data class State<Model>(
        override val data: List<Model>,
        val currentLoadedPage: Int,
        val reachedLastPage: Boolean = false,
    ) : PaginationState<Model>
}
