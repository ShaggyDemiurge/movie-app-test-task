package com.github.shaggydemiurge.movieapp.core.util

import org.slf4j.LoggerFactory

fun logger(tag: () -> String) = lazy { LoggerFactory.getLogger(tag()) }

fun logger(tag: String) = LoggerFactory.getLogger(tag)
