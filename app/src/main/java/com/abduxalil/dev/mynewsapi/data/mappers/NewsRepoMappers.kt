package com.abduxalil.dev.mynewsapi.data.mappers

import com.abduxalil.dev.mynewsapi.data.remote.dao.Article
import com.abduxalil.dev.mynewsapi.domain.entity.NewsRepo


fun List<Article?>.mapToDomain(): List<NewsRepo> {
    return map { article ->
        NewsRepo(
            name = article?.title.orEmpty(),
            description = article?.description.orEmpty(),
            id = article?.source?.id.orEmpty(),
            url = article?.urlToImage.orEmpty()
        )
    }
}
