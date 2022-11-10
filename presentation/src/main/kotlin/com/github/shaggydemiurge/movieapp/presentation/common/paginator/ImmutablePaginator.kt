package com.github.shaggydemiurge.movieapp.presentation.common.paginator

data class ImmutablePaginator<Model, State : ImmutablePaginator.PaginationState<Model>>(
    private val delegate: Delegate<Model, State>,
    val state: State = delegate.createEmptyState(),
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

    fun refresh() = copy(state = delegate.createEmptyState())

    interface PaginationState<Model> {
        val data: List<Model>
    }

    interface Delegate<Model, State> {
        fun createEmptyState(): State
        suspend fun State.loadMore(): State
        fun State.replaceData(newData: List<Model>): State
        fun State.isRequestRequired(): Boolean = true
    }
}
