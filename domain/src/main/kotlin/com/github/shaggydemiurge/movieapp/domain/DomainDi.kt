package com.github.shaggydemiurge.movieapp.domain

import com.github.shaggydemiurge.movieapp.domain.usecase.useCaseModule
import org.koin.dsl.module

val domainModule = module {
    includes(useCaseModule)
}
