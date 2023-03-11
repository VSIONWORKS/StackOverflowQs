package com.exam.stackoverflowqs.ui.item

import android.view.View
import com.exam.stackoverflowqs.R
import com.exam.stackoverflowqs.data.model.Item
import com.exam.stackoverflowqs.databinding.LayoutQuestionItemBinding
import com.exam.stackoverflowqs.utils.load
import com.exam.stackoverflowqs.utils.toFormmattedDate
import com.xwray.groupie.viewbinding.BindableItem

class QuestionItem(private val item: Item, private val onClick: (String) -> Unit) : BindableItem<LayoutQuestionItemBinding>() {
    override fun bind(viewBinding: LayoutQuestionItemBinding, position: Int) {
        viewBinding.apply {
            with(item) {
                civProfile.load(owner.profileImage)
                tvName.text = owner.displayName
                tvTitle.text = title
                tvDate.text = creationDate.toFormmattedDate()
                tvScoreValue.text = score.toString()
                tvAnswerValue.text = answerCount.toString()
                tvViewValue.text = viewCount.toString()
                root.setOnClickListener { onClick.invoke(link) }
            }
        }
    }

    override fun getLayout(): Int = R.layout.layout_question_item

    override fun initializeViewBinding(view: View): LayoutQuestionItemBinding = LayoutQuestionItemBinding.bind(view)
}