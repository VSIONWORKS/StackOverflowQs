package com.exam.stackoverflowqs.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.exam.stackoverflowqs.data.model.Item
import com.exam.stackoverflowqs.data.model.QuestionListModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.util.*

/**
 * Show [View] by Visbile or Gone
 * */
fun View.show(show: Boolean) {
    if (show) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}

/**
 * Show [View] by invisible instead of GONE
 * */
fun View.isInvisible(isInvisible: Boolean) {
    if (isInvisible) this.visibility = View.INVISIBLE
    else this.visibility = View.VISIBLE
}

/**
 * Open external browser using the [String] value
 * */
fun String.browseExternal(context: Context) {
    val browserIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(this)
    )
    context.startActivity(browserIntent)
}

/**
 * Format Long Date to Proper date format
 * */
fun Long.toFormmattedDate(): String {
    val sdf = SimpleDateFormat("MMM dd, yyyy")
    return sdf.format(Date(this * 1000)).toString()
}

/**
 * load image url using default argb_8888
 */
fun ImageView.load(
    url: String,
    requestOptions: RequestOptions = RequestOptions()
        .format(DecodeFormat.PREFER_ARGB_8888),
    placeHolder: Int = -1,
    errorHolder: Int = -1
) {
    Glide.with(context)
        .load(url)
        .apply {
            if (placeHolder >= 0) {
                placeholder(placeHolder)
            }
            if (errorHolder >= 0) {
                error(errorHolder)
            }
        }
        .apply(requestOptions)
        .into(this)
}

/**
 * Launches subscriber when [LifecycleCoroutineScope] is at least in [Lifecycle.State.STARTED] state.
 * */
fun LifecycleOwner.launchOnStart(subscriber: suspend () -> Unit) {
    lifecycleScope.launchWhenStarted {
        subscriber.invoke()
    }
}

/**
 * Launches collect when [LifecycleCoroutineScope] is at least in [Lifecycle.State.STARTED] state.
 * @param subscriber - accepts any method accepting [T] as parameter
 * */
fun <T> Flow<T>.collectOnChange(lifecycleOwner: LifecycleOwner, subscriber: suspend (T) -> Unit) {
    lifecycleOwner.launchOnStart {
        collect {
            subscriber.invoke(it)
        }
    }
}

/**
 * Filter the [MutableStateFlow] data type
 * */
fun MutableStateFlow<QuestionListModel>.filter(isUnAnsweredOnly: Boolean): List<Item> {
    return this.value.items.filter { item ->
        if (isUnAnsweredOnly) item.answerCount == 0 else return@filter true
    }
}

/**
 * Filter newly fetch [items] to prevent duplication of data with the old data
 * */
fun List<Item>.filterDistinct(list: List<Item>): List<Item> {
    return this.filterNot {
        list.any { item -> item.questionId == it.questionId }
    }
}