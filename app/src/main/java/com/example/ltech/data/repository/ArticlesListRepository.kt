package com.example.ltech.data.repository

import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.ltech.app.Application
import com.example.ltech.data.api.ArticlesResponseApi
import com.example.ltech.data.models.database.articlesTable.ArticlesDao
import com.example.ltech.data.models.database.articlesTable.ArticlesEntity
import com.example.ltech.data.worker.ScheduledArticlesRefresh
import com.example.ltech.presentation.models.ArticlesListUiModel
import com.example.ltech.utils.mapFromArticlesResponseToUiModel
import com.example.ltech.utils.mapToEntity
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ArticlesListRepository
@Inject constructor(
    private val articlesApi: ArticlesResponseApi,
    private val articlesDao: ArticlesDao,
    private val application: Application,
) {

    suspend fun deleteFromDb() {
        articlesDao.clearArticlesTable()
    }

    suspend fun getCachedArticles(): List<ArticlesEntity> {
        return articlesDao.getArticles()
    }

    suspend fun loadArticlesListFromApi(): List<ArticlesListUiModel> {
        val emptyList = emptyList<ArticlesListUiModel>()
        try {
            val responseBody = articlesApi.getArticles().body()
            if (responseBody != null) {
                val articlesResponse = responseBody.mapFromArticlesResponseToUiModel()
                deleteFromDb()
                cacheArticlesToEntityFromApi(articlesResponse.mapToEntity())
                return articlesResponse
            }
        } catch (e: Exception) {
            return emptyList
        }
        return emptyList
    }

    private suspend fun cacheArticlesToEntityFromApi(articlesEntityList: List<ArticlesEntity>) {
        articlesDao.insertArticles(articlesEntityList)
    }

    fun refreshArticlesInBackground() {
        val request = PeriodicWorkRequest
            .Builder(ScheduledArticlesRefresh::class.java, 30, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(application).enqueue(request)
    }
}