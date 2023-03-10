package com.exam.stackoverflowqs.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.stackoverflowqs.databinding.ActivityMainBinding
import com.exam.stackoverflowqs.ui.item.QuestionItem
import com.exam.stackoverflowqs.ui.viewmodel.MainViewModel
import com.exam.stackoverflowqs.utils.browseExternal
import com.exam.stackoverflowqs.utils.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.coroutines.flow.collectLatest
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
        lifecycleScope.launchWhenStarted {
            mainViewModel.filteredQuestionsModel.collectLatest {
                val items = it.items.map { item ->
                    QuestionItem(item) { link ->
                        link.browseExternal(this@MainActivity)
                    }
                }
                body.update(items)
                binding.pbLoader.show(it.items.isEmpty())
            }
        }
    }

}