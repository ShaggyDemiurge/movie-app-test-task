package com.github.shaggydemiurge.movieapp.core.ext

import java.util.logging.Logger

fun logger(tag: () -> String) = lazy { Logger.getLogger(tag()) }

fun logger(tag: String) = Logger.getLogger(tag)
