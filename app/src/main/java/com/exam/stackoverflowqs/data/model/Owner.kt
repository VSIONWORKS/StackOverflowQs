package com.exam.stackoverflowqs.data.model

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("account_id") val accountId: Int? = null,
    @SerializedName("reputation") val reputation: Int? = null,
    @SerializedName("user_id") val userId: Int? = null,
    @SerializedName("user_type") val userType: String? = null,
    @SerializedName("accept_rate") val acceptRate: Int? = null,
    @SerializedName("profile_image") val profileImage: String? = null,
    @SerializedName("display_name") val displayName: String? = null,
    @SerializedName("link") val link: String? = null
)
