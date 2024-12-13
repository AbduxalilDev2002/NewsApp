package com.abduxalil.dev.mynewsapi.app.di

import com.abduxalil.dev.mynewsapi.presenter.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::NewsViewModel)
}
