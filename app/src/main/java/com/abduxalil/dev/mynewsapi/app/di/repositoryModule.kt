package com.abduxalil.dev.mynewsapi.app.di

import com.abduxalil.dev.mynewsapi.data.repository.NewsRepositoryImpl
import com.abduxalil.dev.mynewsapi.domain.repository.NewsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::NewsRepositoryImpl) { bind<NewsRepository>() }
}