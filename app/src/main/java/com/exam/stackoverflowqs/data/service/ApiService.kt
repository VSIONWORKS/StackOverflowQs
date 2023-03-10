package com.exam.stackoverflowqs.data.service

import com.exam.stackoverflowqs.data.model.QuestionListModel
import com.exam.stackoverflowqs.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("2.3/questions")
    suspend fun getStackOverflowData(
        @Query("fromdate") fromdate: String,
        @Query("todate") todate: String,
        @Query("order") order: String = "desc",
        @Query("sort") sort: String = "creation",
        @Query("site") site: String = "stackoverflow",
    ): QuestionListModel

    companion object {
        operator fun invoke(): ApiService {

            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            val okHttpClient = OkHttpClient.Builder().addNetworkInterceptor(loggingInterceptor).build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}