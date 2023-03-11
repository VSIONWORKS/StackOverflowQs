package com.exam.stackoverflowqs.core.modules

import com.exam.stackoverflowqs.data.service.ApiService
import org.koin.dsl.module

/**
 * Dependency module for [ApiService]
 * */
val serviceModule = module {
    single { ApiService() }
}
