package com.exam.stackoverflowqs.ui.viewmodel

import com.exam.stackoverflowqs.base.BaseViewModel
import com.exam.stackoverflowqs.data.model.QuestionListModel
import com.exam.stackoverflowqs.domain.StackOverflowRepository
import com.exam.stackoverflowqs.utils.LoadState
import com.exam.stackoverflowqs.utils.filter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel class with injected [StackOverflowRepository] to communicate to Domain layer.
 * Communicates with Domain layer and handles data states
 * */
class MainViewModel(private val repository: StackOverflowRepository) : BaseViewModel(), IMainViewModel {

    private var page: Int = 1
    private var _isUnAnsweredOnly: Boolean = false

    private val questionListModel = MutableStateFlow(QuestionListModel())
    private val _newQuestionListModel = MutableStateFlow(QuestionListModel())
    private val _filteredQuestionsModel = MutableStateFlow(QuestionListModel())

    override val newQuestionListModel = _newQuestionListModel.asStateFlow()
    override val filteredQuestionsModel = _filteredQuestionsModel.asStateFlow()

    override val loadState = MutableStateFlow<LoadState>(LoadState.Loading)

    init {
        load()
    }

    /**
     * Main caller and loader of new data
     * Uses [StackOverflowRepository] to trigger api call and fetch new data.
     * */
    override fun load() {
        safeLaunch(Dispatchers.IO) {
            val newQuestionList = repository.getStackOverflowQuestions(page)
            questionListModel.value.items.addAll(newQuestionList.items)
            val filteredItems = MutableStateFlow(newQuestionList).filter(_isUnAnsweredOnly)
            _newQuestionListModel.value = _newQuestionListModel.value.copy(items = ArrayList(filteredItems), timeMillis = System.currentTimeMillis())
            page++
            loadState.value = LoadState.Completed
        }
    }

    /**
     * Method to trigger the filtering of list
     * @param isUnAnsweredOnly - condition on what items to be displayed.
     * */
    override fun onFilter(isUnAnsweredOnly: Boolean) {
        safeLaunch(Dispatchers.IO) {
            _isUnAnsweredOnly = isUnAnsweredOnly
            val filteredItems = questionListModel.filter(isUnAnsweredOnly)
            val filteredQuestions = questionListModel.value.copy(items = ArrayList(filteredItems), timeMillis = System.currentTimeMillis())
            _filteredQuestionsModel.value = filteredQuestions
        }
    }

    /**
     * Override Method to catch api call errors and handle loading state
     * */
    override fun onExceptionReceived(e: Throwable) {
        super.onExceptionReceived(e)
        loadState.value = LoadState.Error
    }
}