package com.exam.stackoverflowqs.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.stackoverflowqs.data.model.QuestionListModel
import com.exam.stackoverflowqs.databinding.ActivityMainBinding
import com.exam.stackoverflowqs.ui.item.FooterItem
import com.exam.stackoverflowqs.ui.item.QuestionItem
import com.exam.stackoverflowqs.ui.viewmodel.MainViewModel
import com.exam.stackoverflowqs.utils.Constants.NETWORK_ERROR
import com.exam.stackoverflowqs.utils.LoadState
import com.exam.stackoverflowqs.utils.browseExternal
import com.exam.stackoverflowqs.utils.collectOnChange
import com.exam.stackoverflowqs.utils.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()
    private val body = Section()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.setupUi()
        collectData()
    }

    private fun ActivityMainBinding.setupUi() {
        rvQuestionList.apply {
            adapter = GroupAdapter<GroupieViewHolder>().apply {
                add(body)
            }
            layoutManager = LinearLayoutManager(context)
        }

        cbUnansweredOnly.setOnCheckedChangeListener { _, isChecked ->
            mainViewModel.onFilter(isChecked)
        }
    }

    private fun collectData() {
        with(mainViewModel) {
            filteredQuestionsModel.collectOnChange(this@MainActivity) {
                it.loadItems()
            }
            newQuestionListModel.collectOnChange(this@MainActivity) {
                it.loadItems(true)
            }
            loadState.collectOnChange(this@MainActivity) {
                handleLoadState(it)
            }
        }
    }

    private fun QuestionListModel.loadItems(isNewList: Boolean = false) {

        val items = this.items.map { item ->
            QuestionItem(item) { link ->
                link.browseExternal(this@MainActivity)
            }
        }

        val footerItem = if (this.items.isNotEmpty()) {
            FooterItem(show = true) {
                mainViewModel.load()
            }
        } else FooterItem {}

        body.setFooter(footerItem)

        if (isNewList) {
            body.addAll(items)
        } else {
            body.update(items)
        }
    }

    private fun handleLoadState(state: LoadState) {
        binding.apply {
            when (state) {
                LoadState.Loading -> pbLoader.show(true)
                LoadState.Completed -> pbLoader.show(false)
                LoadState.Error -> {
                    Toast.makeText(this@MainActivity, NETWORK_ERROR, Toast.LENGTH_LONG).show()
                    pbLoader.show(false)
                }
            }
        }
    }

}