package com.example.ltech.data.repository

import com.example.ltech.data.api.AuthenticationApi
import com.example.ltech.data.models.database.authenticationTable.success.AuthenticationSuccessDao
import com.example.ltech.data.models.database.authenticationTable.success.AuthenticationSuccessEntity
import com.example.ltech.data.models.remote.authentication.mapFromSuccessAuthenticationResponseToEntity
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val authenticationApi: AuthenticationApi,
    private val authenticationSuccessDao: AuthenticationSuccessDao,
) {

    suspend fun loadLoginStateFromApi(
        phone: String,
        password: String,
    ): Boolean? {
        val responseBody = authenticationApi.login(phone, password).body()
        val cache = responseBody?.mapFromSuccessAuthenticationResponseToEntity()
        val responseBodySuccess = responseBody?.success
        if (cache != null) {
            cacheAuthenticationSuccessFromApi(cache)
        }
        return responseBodySuccess
    }

    private suspend fun cacheAuthenticationSuccessFromApi(
        authenticationSuccessEntity: AuthenticationSuccessEntity,
    ) {
        authenticationSuccessDao.insertAuthenticationSuccess(authenticationSuccessEntity)
    }

    suspend fun loadMask(): String {
        return authenticationApi.getPhoneMaskFromApi().phoneMask
    }
}