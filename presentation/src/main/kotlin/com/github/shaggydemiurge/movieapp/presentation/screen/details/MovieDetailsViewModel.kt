package com.github.shaggydemiurge.movieapp.presentation.screen.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.shaggydemiurge.movieapp.core.entities.MovieDetails
import com.github.shaggydemiurge.movieapp.core.usecase.LoadMovieDetails
import com.github.shaggydemiurge.movieapp.presentation.common.entity.Loadable
import com.github.shaggydemiurge.movieapp.presentation.common.ext.load
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

class MovieDetailsViewModel(
    movieId: MovieId,
    private val loadMovieDetails: LoadMovieDetails,
) : ViewModel() {

    private val movieId = movieId.id

    var movieDetails by mutableStateOf<Loadable<MovieDetails>>(Loadable.Loading())
        private set

    init {
        load(::movieDetails::set) {
            loadMovieDetails(this.movieId)
        }
    }

    companion object {
        val module = module {
            viewModelOf(::MovieDetailsViewModel)
        }
    }
}
