package com.exam.stackoverflowqs.ui.viewmodel

import com.exam.stackoverflowqs.data.model.QuestionListModel
import com.exam.stackoverflowqs.utils.QuestionListState
import kotlinx.coroutines.flow.StateFlow

interface IMainViewModel {

    val questionListState: StateFlow<QuestionListState>
    val filteredQuestionsModel: StateFlow<QuestionListModel>

    fun load()
    fun onFilter(isUnAnsweredOnly: Boolean)
}