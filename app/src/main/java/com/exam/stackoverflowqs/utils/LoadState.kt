package com.exam.stackoverflowqs.utils

sealed class LoadState {
    object Loading : LoadState()
    object Completed : LoadState()
    object Error : LoadState()
}