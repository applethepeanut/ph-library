package com.atpfury.phlibrary.kotlin

sealed interface Result<out T>
data object Success : Result<Nothing>
data class Failure<out T>(val reason: T) : Result<T>