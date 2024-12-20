package com.abduxalil.dev.mynewsapi.data.remote.dao


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsRepoItemResponse(
    @SerialName("articles")
    val articles: List<Article?>? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("totalResults")
    val totalResults: Int? = null
)