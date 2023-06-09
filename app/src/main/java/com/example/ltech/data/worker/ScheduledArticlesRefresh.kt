package com.example.ltech.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.ltech.data.repository.ArticlesListRepository

@HiltWorker
class ScheduledArticlesRefresh @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: ArticlesListRepository,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        withContext(Dispatchers.IO) {
            repository.loadArticlesListFromApi()
        }
        return Result.success()
    }
}