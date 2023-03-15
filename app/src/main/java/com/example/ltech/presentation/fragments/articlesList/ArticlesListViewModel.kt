package com.example.ltech.presentation.fragments.articlesList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ltech.R
import com.example.ltech.app.Application
import com.example.ltech.data.repository.ArticlesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.ltech.presentation.BaseViewModel
import com.example.ltech.presentation.models.ArticlesListUiModel
import com.example.ltech.utils.ArticlesState
import com.example.ltech.utils.InternetConnectionManager
import com.example.ltech.utils.mapFromArticlesEntityToUiModel
import javax.inject.Inject

@HiltViewModel
class ArticlesListViewModel @Inject constructor(
    private val articlesListRepository: ArticlesListRepository,
    internetConnectionManager: InternetConnectionManager,
    application: Application
) : BaseViewModel(internetConnectionManager, application) {

    private val _articlesState = MutableStateFlow<ArticlesState>(ArticlesState.Loading)
    val articlesState: StateFlow<ArticlesState> = _articlesState

    private val _listArticles = MutableLiveData<List<ArticlesListUiModel>>()
    val listArticles: MutableLiveData<List<ArticlesListUiModel>> = _listArticles

    init {
        isHasInternetConnection()
        isAirplaneModeOn()
        Log.d("Articles Worker", " ArticlesListViewModelCreated")
        refreshArticlesInBackground()
        loadArticles()
    }

    fun loadArticles() = viewModelScope.launch {
        val result = articlesListRepository.loadArticlesListFromApi()
        val cache = articlesListRepository.getCachedArticles().mapFromArticlesEntityToUiModel()
        try {
            if (cache.isNotEmpty()) {
                _listArticles.postValue(cache)
                _articlesState.value = ArticlesState.Data
            } else {
                _listArticles.postValue(result)
                _articlesState.value = ArticlesState.Data
            }
        } catch (e: Exception) {
            _articlesState.value = ArticlesState.Error(getApplication<Application>().getString(R.string.error_answer))
            Log.e("Articles List Exception", "$e")
        }
    }

    fun deleteFromTab() {
        viewModelScope.launch(Dispatchers.IO) {
            articlesListRepository.deleteFromDb()
        }
    }

    private fun refreshArticlesInBackground() {
        articlesListRepository.refreshArticlesInBackground()
    }
}


