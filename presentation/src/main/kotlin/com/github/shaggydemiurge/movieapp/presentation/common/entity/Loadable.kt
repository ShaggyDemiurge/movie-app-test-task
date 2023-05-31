package com.github.shaggydemiurge.movieapp.presentation.common.entity

sealed interface Loadable<out T> {
    val value: T?

    fun <R> map(f: (T) -> R): Loadable<R>

    data class Success<T>(override val value: T) : Loadable<T> {
        override fun <R> map(f: (T) -> R) = Success(f(value))
    }

    data class Error<T>(val error: Throwable) : Loadable<T> {
        override val value = null

        override fun <R> map(f: (T) -> R) = Error<R>(error)
    }

    object Loading : Loadable<Nothing> {
        override val value = null

        override fun <R> map(f: (Nothing) -> R) = Loading
    }
}
