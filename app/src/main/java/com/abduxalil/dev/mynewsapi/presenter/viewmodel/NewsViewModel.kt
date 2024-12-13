package com.abduxalil.dev.mynewsapi.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abduxalil.dev.mynewsapi.domain.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewsState())
    val uiState = _uiState.asStateFlow()

    init {
        onFetchNewsRepos(_uiState.value.inputText)
    }

    fun onFetchNewsRepos(inputText: String) {
        _uiState.update { state -> state.copy(inputText = inputText) }
        viewModelScope.launch {
            newsRepository.getNewsRepos(uiState.value.inputText).onStart {
                _uiState.update { state -> state.copy(isLoading = true, isError = false) }
            }.onCompletion {
                _uiState.update { state -> state.copy(isLoading = false) }
            }.collect { result ->
                result.onSuccess { repos ->
                    _uiState.update { state -> state.copy(newsRepos = repos) }
                }
                result.onFailure {
                    _uiState.update { state -> state.copy(isError = true) }
                }
            }
        }
    }
}
