package com.exam.stackoverflowqs.domain

import com.exam.stackoverflowqs.data.model.QuestionListModel

interface StackOverflowRepository {
    suspend fun getStackOverflowQuestions(page: Int): QuestionListModel
}