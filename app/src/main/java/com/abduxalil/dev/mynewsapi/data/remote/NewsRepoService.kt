package com.abduxalil.dev.mynewsapi.data.remote

import com.abduxalil.dev.mynewsapi.data.remote.dao.NewsRepoItemResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class NewsRepoService(val httpClient: HttpClient) {
    suspend fun getNewsRepo(inputText: String = "") =
        httpClient.get("$SQUARE_REPO_URL?q=$inputText&$API_KEY").body<NewsRepoItemResponse>()
}

const val SQUARE_REPO_URL = "https://newsapi.org/v2/everything"
const val API_KEY = "apiKey=11bde3bff2e9435395a578aa076108b6"

