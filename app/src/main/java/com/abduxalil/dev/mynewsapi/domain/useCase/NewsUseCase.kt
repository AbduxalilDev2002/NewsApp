package com.abduxalil.dev.mynewsapi.domain.useCase

import com.abduxalil.dev.mynewsapi.domain.entity.NewsRepo
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    suspend fun getAllNews(): Flow<Result<List<NewsRepo>>>
}