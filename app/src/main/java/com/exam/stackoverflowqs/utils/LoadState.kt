package com.exam.stackoverflowqs.utils

import com.exam.stackoverflowqs.data.model.QuestionListModel

sealed class QuestionListState {
    object Loading : QuestionListState()
    object Completed : QuestionListState()
    object Error : QuestionListState()
}

//sealed class QuestionListState {
//    object Loading : QuestionListState()
//    data class Success(val questionList: QuestionListModel) : QuestionListState()
//    object Error : QuestionListState()
//}