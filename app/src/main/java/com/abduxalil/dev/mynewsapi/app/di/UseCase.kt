package com.abduxalil.dev.mynewsapi.app.di

import com.abduxalil.dev.mynewsapi.domain.useCase.NewsFilterUseCase
import com.abduxalil.dev.mynewsapi.domain.useCase.NewsFilterUseCaseImpl
import org.koin.dsl.module

val newsFilter = module {
    factory<NewsFilterUseCase> { NewsFilterUseCaseImpl(get()) }
}