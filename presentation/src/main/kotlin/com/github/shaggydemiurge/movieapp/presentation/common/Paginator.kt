package com.github.shaggydemiurge.movieapp.presentation.common

import com.github.shaggydemiurge.movieapp.core.util.provideDelegate
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
 * A base for pagination classes implementing basic actions and allowing to override definition for data and state
 * for subclasses
 */
abstract class Paginator<Model, State : Paginator.PaginationState<Model>> {

    /**
     * Current data saved in this paginator
     */
    val currentData get() = state.data

    fun subscribeToCurrentData(): Flow<List<Model>> = stateFlow
        .onStart { emit(state) } // To initialize stateFlow if it wasn't initialized yet
        .filterNotNull()
        .map { it.data }

    val isLoading get() = requestCouple != null

    fun subscribeToLoading(): Flow<Boolean> = requestFlow.map { it != null }

    fun subscribeToErrors(): Flow<Throwable> = errorFlow

    private val stateFlow = MutableStateFlow<State?>(null)

    private val requestFlow = MutableStateFlow<RequestCouple<State>?>(null)

    private val errorFlow = MutableSharedFlow<Throwable>()

    private var requestCouple by requestFlow

    /**
     * Current state of this paginator
     */
    protected var state
        get() = stateFlow.value ?: createEmptyState().also { stateFlow.value = it }
        set(value) {
            stateFlow.value = value
        }

    /**
     * Save new data, but keep the rest of the state as it was
     */
    protected abstract fun replaceDataset(state: State, newDataset: List<Model>): State

    /**
     * Save new data, but keep the rest of the state as it was
     */
    fun replaceDataset(newDataset: List<Model>, cancelLoading: Boolean = false) {
        changeState(replaceDataset(state, newDataset), cancelLoading)
    }

    /**
     * Replaces single item in saved data
     */
    fun replaceItem(oldItem: Model, newItem: Model, cancelLoading: Boolean = false) {
        replaceDataset(currentData.map { if (it == oldItem) newItem else it }, cancelLoading)
    }

    /**
     * Retrieve new data based on current [state] of paginator
     */
    protected abstract suspend fun updateStateWithNewData(state: State): State

    /**
     * Create a new completely clean state as if paginator never received or requested any data yet
     */
    protected abstract fun createEmptyState(): State

    /**
     * Check if there's need to retrieve new data according to current [state]. If it returns `false`,
     * new request is cancelled
     */
    protected open fun requestRequired(state: State): Boolean = true

    /**
     * Save [newState] as current state, and cancel current pagination process if [cancelJob] is true
     */
    protected fun changeState(newState: State, cancelJob: Boolean) {
        if (cancelJob) {
            requestCouple?.job?.cancel()
        }
        this.state = newState
    }

    private suspend fun loadData(currentState: () -> State): State = coroutineScope {
        val requestCouple = this@Paginator.requestCouple
        val previousState = currentState()
        if (requestCouple != null && previousState == requestCouple.requestedState) {
            return@coroutineScope requestCouple.job.await()
        } else {
            requestCouple?.job?.cancel()

            val newCouple = RequestCouple(
                async {
                    if (requestRequired(previousState)) {
                        updateStateWithNewData(previousState)
                    } else {
                        previousState
                    }
                        .also {
                            this@Paginator.state = it
                            this@Paginator.requestCouple = null
                        }
                },
                previousState
            )
            this@Paginator.requestCouple = newCouple
            try {
                newCouple.job.await()
            } catch (e: Exception) {
                previousState
            }
        }
    }

    /**
     * Forces paginator to load more data according to current state using [updateStateWithNewData]
     */
    suspend fun requestMore(): List<Model> =
        loadData { state }.data

    /**
     * Resets all data and saved pagination state and requests info anew using [updateStateWithNewData]
     */
    suspend fun refresh(): List<Model> =
        loadData { createEmptyState() }.data

    /**
     * Representation of current pagination state of this paginator. It should be the only thing that determines
     * what data is retrieved. Things like current offset, or id of last received element go there
     */
    interface PaginationState<Model> {
        /**
         * Current data retrieved by paginator by previous requests
         */
        val data: List<Model>
    }

    private data class RequestCouple<State>(val job: Deferred<State>, val requestedState: State)
}
