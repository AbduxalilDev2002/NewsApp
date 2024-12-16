package com.abduxalil.dev.mynewsapi.domain.useCase

import com.abduxalil.dev.mynewsapi.domain.entity.NewsRepo
import kotlinx.coroutines.flow.Flow

interface NewsFilterUseCase {
    fun filterNews(inputText: String): Flow<Result<List<NewsRepo>>>
}