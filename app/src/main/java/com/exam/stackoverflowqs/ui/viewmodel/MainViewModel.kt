package com.exam.stackoverflowqs.ui.viewmodel

import android.util.Log
import com.exam.stackoverflowqs.base.BaseViewModel
import com.exam.stackoverflowqs.data.model.QuestionListModel
import com.exam.stackoverflowqs.domain.StackOverflowRepository
import com.exam.stackoverflowqs.utils.QuestionListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.sign

class MainViewModel(private val repository: StackOverflowRepository) : BaseViewModel(), IMainViewModel {

    override val questionListState = MutableStateFlow<QuestionListState>(QuestionListState.Completed)
    override val questionListModel = MutableStateFlow(QuestionListModel())
    override val filteredQuestionsModel = MutableStateFlow(QuestionListModel())

    init {
        load()
    }

    override fun load() {
        safeLaunch(Dispatchers.IO) {
            questionListModel.value = repository.getStackOverflowQuestions()
            filteredQuestionsModel.value = questionListModel.value

            Log.e("questions data : ", questionListModel.toString())
        }
    }


    fun onFilter(isUnAnsweredOnly: Boolean) {
        val filteredItems = questionListModel.value.copy().items.filter { item ->
            if (isUnAnsweredOnly) item.answerCount == 0 else return@filter true
        }

        val filteredQuestions = questionListModel.value.copy(items = ArrayList(filteredItems))
        filteredQuestionsModel.value = filteredQuestions

    }

}