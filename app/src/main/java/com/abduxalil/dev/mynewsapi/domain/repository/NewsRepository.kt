package com.abduxalil.dev.mynewsapi.domain.repository

import com.abduxalil.dev.mynewsapi.domain.entity.NewsRepo
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNewsRepos(inputText: String): Flow<Result<List<NewsRepo>>>
}
