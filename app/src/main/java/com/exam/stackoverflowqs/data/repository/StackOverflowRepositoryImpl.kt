package com.exam.stackoverflowqs.data.repository

import com.exam.stackoverflowqs.data.model.QuestionListModel
import com.exam.stackoverflowqs.data.service.ApiService
import com.exam.stackoverflowqs.domain.StackOverflowRepository
import java.time.LocalDate
import java.time.ZoneOffset


class StackOverflowRepositoryImpl(private val service: ApiService) : StackOverflowRepository {

    override suspend fun getStackOverflowQuestions(): QuestionListModel {
        val toDate = LocalDate.now().atStartOfDay().toEpochSecond(ZoneOffset.UTC).toString()
        val fromDate = LocalDate.now().minusDays(30).atStartOfDay().toEpochSecond(ZoneOffset.UTC).toString()
        return service.getStackOverflowData(fromdate = fromDate, todate = toDate)
    }
}