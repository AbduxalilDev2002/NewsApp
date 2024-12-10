package com.abduxalil.dev.mynewsapi.presenter

import androidx.compose.runtime.Immutable
import com.abduxalil.dev.mynewsapi.domain.entity.NewsRepo


@Immutable
data class NewsState(
    val newsRepos: List<NewsRepo> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    var inputText: String = "test"
)
