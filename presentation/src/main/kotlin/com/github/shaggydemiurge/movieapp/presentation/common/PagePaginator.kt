package com.github.shaggydemiurge.movieapp.presentation.common

class PagePaginator<Model>(val dataProvider: suspend (page: Int) -> List<Model>) :
    Paginator<Model, PagePaginator.State<Model>>() {

    val reachedEnd get() = state.reachedLastPage

    override fun replaceDataset(state: State<Model>, newDataset: List<Model>): State<Model> =
        state.copy(data = newDataset)

    override suspend fun updateStateWithNewData(state: State<Model>): State<Model> {
        val newData = dataProvider(state.currentLoadedPage + 1)
        return State(
            state.data + newData,
            if (newData.isEmpty()) state.currentLoadedPage else state.currentLoadedPage + 1,
            newData.isEmpty()
        )
    }

    override fun createEmptyState(): State<Model> = State(emptyList())

    override fun requestRequired(state: State<Model>): Boolean = !state.reachedLastPage

    data class State<Model>(
        override val data: List<Model>,
        val currentLoadedPage: Int = -1,
        val reachedLastPage: Boolean = false,
    ) : Paginator.PaginationState<Model>
}
