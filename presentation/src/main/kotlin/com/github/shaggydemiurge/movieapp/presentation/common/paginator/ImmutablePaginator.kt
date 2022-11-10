package com.github.shaggydemiurge.movieapp.presentation.common.paginator

@Suppress("MemberVisibilityCanBePrivate")
data class ImmutablePaginator<Model, State : ImmutablePaginator.PaginationState<Model>>(
    val state: State,
    private val onLoadMore: suspend (State) -> State,
    private val onReplaceData: (State, newData: List<Model>) -> State,
    private val isRequestRequired: (State) -> Boolean = { true },
) {

    val data get() = state.data

    suspend fun requestMore(): ImmutablePaginator<Model, State> {
        if (!isRequestRequired(state)) return this
        return copy(state = onLoadMore(state))
    }

    fun replaceDataset(data: List<Model>) = copy(state = onReplaceData(state, data))

    fun updateItem(newItem: Model, oldItemSelector: (Model) -> Boolean) = replaceDataset(
        data.map { if (oldItemSelector(it)) newItem else it }
    )

    interface PaginationState<Model> {
        val data: List<Model>
    }
}
