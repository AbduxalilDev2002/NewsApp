//package com.abduxalil.dev.mynewsapi.domain.useCase
//
//import com.abduxalil.dev.mynewsapi.data.remote.NewsRepoService
//import com.abduxalil.dev.mynewsapi.data.remote.dao.NewsRepoItemResponse
//import com.abduxalil.dev.mynewsapi.domain.entity.NewsRepo
//import com.abduxalil.dev.mynewsapi.domain.repository.NewsRepository
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.filter
//import kotlinx.coroutines.flow.map
//
//class GetNewsReposUseCase(
//    private val newsRepository: NewsRepository
//) {
//    suspend fun execute(inputText: String): Flow<Result<List<NewsRepo>>> {
//        return newsRepository.getNewsRepos(inputText).map { result ->
//            result.map { newsList ->
//                newsList.filter { item ->
//                    item.name.contains(inputText, ignoreCase = true) &&
//                            !item.name.contains("Removed", ignoreCase = true) &&
//                            (item.description == null || !item.description.contains("Removed", ignoreCase = true))
//                }
//            }
//        }
//    }
//}
