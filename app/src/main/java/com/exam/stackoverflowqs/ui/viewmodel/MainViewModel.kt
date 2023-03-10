package com.exam.stackoverflowqs.ui.viewmodel

import android.util.Log
import com.exam.stackoverflowqs.base.BaseViewModel
import com.exam.stackoverflowqs.data.model.QuestionListModel
import com.exam.stackoverflowqs.domain.StackOverflowRepository
import com.exam.stackoverflowqs.utils.QuestionListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.sign

class MainViewModel(private val repository: StackOverflowRepository) : BaseViewModel(), IMainViewModel {

    private val questionListModel = MutableStateFlow(QuestionListModel())
    private val _filteredQuestionsModel = MutableStateFlow(QuestionListModel())
    override val filteredQuestionsModel = _filteredQuestionsModel.asStateFlow()

    override val questionListState = MutableStateFlow<QuestionListState>(QuestionListState.Completed)

    init {
        load()
    }

    override fun load() {
        safeLaunch(Dispatchers.IO) {
            questionListModel.value = repository.getStackOverflowQuestions()
            _filteredQuestionsModel.value = questionListModel.value

            Log.e("questions data : ", questionListModel.toString())
        }
    }


    override fun onFilter(isUnAnsweredOnly: Boolean) {
        val filteredItems = questionListModel.value.copy().items.filter { item ->
            if (isUnAnsweredOnly) item.answerCount == 0 else return@filter true
        }

        val filteredQuestions = questionListModel.value.copy(items = ArrayList(filteredItems))
        _filteredQuestionsModel.value = filteredQuestions

    }

}