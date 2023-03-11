package com.exam.stackoverflowqs.ui.item

import android.view.View
import com.exam.stackoverflowqs.R
import com.exam.stackoverflowqs.databinding.LayoutFooterItemBinding
import com.exam.stackoverflowqs.utils.isInvisible
import com.exam.stackoverflowqs.utils.show
import com.xwray.groupie.viewbinding.BindableItem

class FooterItem(private val show: Boolean = false, private val onClick: () -> Unit) : BindableItem<LayoutFooterItemBinding>() {

    override fun bind(viewBinding: LayoutFooterItemBinding, position: Int) {
        viewBinding.apply {
            root.setOnClickListener {
                onClick.invoke()
                tvLoadMore.isInvisible(true)
                pbLoadMore.isInvisible(false)
            }
            showLoader()
        }
    }

    private fun LayoutFooterItemBinding.showLoader() {
        apply {
            root.show(show)
            tvLoadMore.isInvisible(false)
            pbLoadMore.isInvisible(true)
        }
    }

    override fun getLayout(): Int = R.layout.layout_footer_item

    override fun initializeViewBinding(view: View): LayoutFooterItemBinding = LayoutFooterItemBinding.bind(view)
}