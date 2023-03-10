package com.exam.stackoverflowqs.data.model

import com.google.gson.annotations.SerializedName

data class QuestionListModel(
    @SerializedName("items") val items: ArrayList<Item> = arrayListOf(),
    @SerializedName("has_more") val hasMore: Boolean? = null,
    @SerializedName("quota_max") val quotaMax: Int? = null,
    @SerializedName("quota_remaining") val quotaRemaining: Int? = null
)
