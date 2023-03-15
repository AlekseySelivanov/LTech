package com.example.ltech.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.ltech.utils.InternetConnectionManager

abstract class BaseViewModel(
    private val internetConnectionManager: InternetConnectionManager,
    application: Application,
) : AndroidViewModel(application) {

    fun isHasInternetConnection(): Boolean =
        internetConnectionManager.isHasInternetConnection()

    fun isAirplaneModeOn(): Boolean =
        internetConnectionManager.isAirplaneModeOn()
}