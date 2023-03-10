package com.exam.stackoverflowqs.ui.item

import android.view.View
import com.exam.stackoverflowqs.R
import com.exam.stackoverflowqs.data.model.Item
import com.exam.stackoverflowqs.databinding.LayoutQuestionItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class QuestionItem(private val item: Item, private val onClick: (String) -> Unit) : BindableItem<LayoutQuestionItemBinding>() {
    override fun bind(viewBinding: LayoutQuestionItemBinding, position: Int) {
        viewBinding.apply {
            tvTitle.text = item.title
            tvAnswerLabel.text = "Answers : ${item.answerCount}"
            root.setOnClickListener { onClick.invoke(item.link) }
        }
    }

    override fun getLayout(): Int = R.layout.layout_question_item

    override fun initializeViewBinding(view: View): LayoutQuestionItemBinding = LayoutQuestionItemBinding.bind(view)
}