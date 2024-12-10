package com.abduxalil.dev.mynewsapi.data.repository

import com.abduxalil.dev.mynewsapi.data.mappers.mapToDomain
import com.abduxalil.dev.mynewsapi.data.remote.NewsRepoService
import com.abduxalil.dev.mynewsapi.domain.repository.NewsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl(
    private val newsRepoService: NewsRepoService
) : NewsRepository {
    override fun getNewsRepos(inputText: String) = flow {
        emit(
            Result.success(
                newsRepoService.getNewsRepo(inputText = inputText).articles?.mapToDomain()
                    ?: emptyList()
            )
        )
    }.catch { throwable ->
        emit(Result.failure(throwable))
    }
}
