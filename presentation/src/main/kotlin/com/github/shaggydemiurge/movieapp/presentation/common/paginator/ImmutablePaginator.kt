package com.github.shaggydemiurge.movieapp.presentation.common.paginator

data class ImmutablePaginator<Model, State : ImmutablePaginator.PaginationState<Model>>(
    val state: State,
    private val delegate: Delegate<Model, State>,
) {

    val data get() = state.data

    suspend fun requestMore(): ImmutablePaginator<Model, State> {
        with(delegate) {
            if (!state.isRequestRequired()) return this@ImmutablePaginator
            return copy(state = state.loadMore())
        }
    }

    fun replaceDataset(data: List<Model>) = with(delegate) { copy(state = state.replaceData(data)) }

    fun updateItem(newItem: Model, oldItemSelector: (Model) -> Boolean) = replaceDataset(
        data.map { if (oldItemSelector(it)) newItem else it }
    )

    interface PaginationState<Model> {
        val data: List<Model>
    }

    interface Delegate<Model, State> {

        suspend fun State.loadMore(): State
        fun State.replaceData(newData: List<Model>): State
        fun State.isRequestRequired(): Boolean = true
    }
}
