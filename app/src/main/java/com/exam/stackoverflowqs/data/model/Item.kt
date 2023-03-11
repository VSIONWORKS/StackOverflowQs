package com.exam.stackoverflowqs.data.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("tags") val tags: ArrayList<String> = arrayListOf(),
    @SerializedName("owner") val owner: Owner = Owner(),
    @SerializedName("is_answered") val isAnswered: Boolean? = null,
    @SerializedName("view_count") val viewCount: Int? = null,
    @SerializedName("answer_count") val answerCount: Int = 0,
    @SerializedName("score") val score: Int? = null,
    @SerializedName("last_activity_date") val lastActivityDate: Int? = null,
    @SerializedName("creation_date") val creationDate: Long = 0,
    @SerializedName("question_id") val questionId: Int? = null,
    @SerializedName("content_license") val contentLicense: String? = null,
    @SerializedName("link") val link: String = "",
    @SerializedName("title") val title: String? = null
)
