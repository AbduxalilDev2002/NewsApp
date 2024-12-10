package com.abduxalil.dev.mynewsapi.app.di

import com.abduxalil.dev.mynewsapi.data.remote.NewsRepoService
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val networkModule = module {
    single<HttpClient> {
        HttpClient(OkHttp) {
            engine {
                addInterceptor(
                    ChuckerInterceptor.Builder(androidContext())
                        .collector(ChuckerCollector(androidContext()))
                        .maxContentLength(250000L)
                        .redactHeaders(emptySet())
                        .alwaysReadResponseBody(false)
                        .build()
                )
            }
            install(Logging)
            install(WebSockets)
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                        encodeDefaults = true
                    }
                )
            }
        }
    }

    single {
        NewsRepoService(httpClient = get())
    }
}
