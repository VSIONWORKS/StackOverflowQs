package com.exam.stackoverflowqs.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Object model for parsing of JsonResponse with
 * @param timeMillis - use as helper to trigger update of [MutableStateFlow]
 * when the changes in object are inside the child object
 * */
data class QuestionListModel(
    @SerializedName("items") val items: ArrayList<Item> = arrayListOf(),
    @SerializedName("has_more") val hasMore: Boolean? = null,
    @SerializedName("quota_max") val quotaMax: Int? = null,
    @SerializedName("quota_remaining") val quotaRemaining: Int? = null,
    val timeMillis: Long = System.currentTimeMillis()
)
