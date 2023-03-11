package com.exam.stackoverflowqs.ui.viewmodel

import com.exam.stackoverflowqs.data.model.QuestionListModel
import com.exam.stackoverflowqs.utils.LoadState
import kotlinx.coroutines.flow.StateFlow

/**
 * Interface to be override on the [MainViewModel]
 * */
interface IMainViewModel {

    val loadState: StateFlow<LoadState>
    val newQuestionListModel: StateFlow<QuestionListModel>
    val filteredQuestionsModel: StateFlow<QuestionListModel>

    fun load()
    fun onFilter(isUnAnsweredOnly: Boolean)
}