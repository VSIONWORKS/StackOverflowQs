package com.exam.stackoverflowqs.domain

import com.exam.stackoverflowqs.data.model.QuestionListModel

/**
 * Domain layer class to implement [StackOverflowRepositoryImpl]
 * */
interface StackOverflowRepository {
    suspend fun getStackOverflowQuestions(page: Int): QuestionListModel
}