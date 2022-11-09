package com.github.shaggydemiurge.movieapp.core.entities

data class PagedResult<T>(val data: List<T>, val page: Int, val maxPages: Int)
