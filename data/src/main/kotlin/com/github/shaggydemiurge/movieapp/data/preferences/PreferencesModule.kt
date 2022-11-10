package com.github.shaggydemiurge.movieapp.data.preferences

import android.content.Context
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val preferencesModule = module {
    single {
        val context = get<Context>()
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }
    singleOf(::AppPreferences)
}
