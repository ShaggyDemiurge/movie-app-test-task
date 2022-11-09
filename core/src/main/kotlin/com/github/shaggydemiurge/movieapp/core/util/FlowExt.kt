package com.github.shaggydemiurge.movieapp.core.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

operator fun <T> MutableStateFlow<T>.provideDelegate(thisRef: Any, property: KProperty<*>) =
    object : ReadWriteProperty<Any, T> {
        override fun getValue(thisRef: Any, property: KProperty<*>): T = this@provideDelegate.value

        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
            this@provideDelegate.value = value
        }
    }
