package com.abduxalil.dev.mynewsapi.domain.useCase

import com.abduxalil.dev.mynewsapi.domain.entity.NewsRepo
import com.abduxalil.dev.mynewsapi.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsFilterUseCaseImpl(
    private val newsRepository: NewsRepository
):NewsFilterUseCase {
    override fun filterNews(inputText: String): Flow<Result<List<NewsRepo>>> {
        return newsRepository.getNewsRepos(inputText).map { result ->
            result.map { newsList ->
                newsList.filter { it.name != "[Removed]" }
            }
        }
    }
}