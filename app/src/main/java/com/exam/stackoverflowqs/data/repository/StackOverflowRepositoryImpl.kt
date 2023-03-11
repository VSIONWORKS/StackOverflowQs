package com.exam.stackoverflowqs.data.repository

import com.exam.stackoverflowqs.data.model.QuestionListModel
import com.exam.stackoverflowqs.data.service.ApiService
import com.exam.stackoverflowqs.domain.StackOverflowRepository
import com.exam.stackoverflowqs.utils.Constants.LAST_DAYS_NO
import java.time.LocalDate
import java.time.ZoneOffset


class StackOverflowRepositoryImpl(private val service: ApiService) : StackOverflowRepository {

    override suspend fun getStackOverflowQuestions(page: Int): QuestionListModel {
        val toDate = LocalDate.now().atStartOfDay().toEpochSecond(ZoneOffset.UTC).toString()
        val fromDate = LocalDate.now().minusDays(LAST_DAYS_NO.toLong()).atStartOfDay().toEpochSecond(ZoneOffset.UTC).toString()
        return service.getStackOverflowData(page = page, fromDate = fromDate, toDate = toDate)
    }
}