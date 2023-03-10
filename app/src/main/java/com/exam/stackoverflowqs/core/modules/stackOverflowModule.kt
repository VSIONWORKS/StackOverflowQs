package com.exam.stackoverflowqs.core.modules

import com.exam.stackoverflowqs.data.repository.StackOverflowRepositoryImpl
import com.exam.stackoverflowqs.domain.StackOverflowRepository
import com.exam.stackoverflowqs.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val stackOverflowModule = module {
    factory<StackOverflowRepository> { StackOverflowRepositoryImpl(get()) }
    viewModel { MainViewModel(get()) }
}