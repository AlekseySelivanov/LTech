package com.example.ltech.data.models.remote.authentication

import com.google.gson.annotations.SerializedName
import com.example.ltech.data.models.database.authenticationTable.success.AuthenticationSuccessEntity

data class AuthenticationSuccessResponse(
    @SerializedName("success")
    val success: Boolean,
)

fun AuthenticationSuccessResponse.mapFromSuccessAuthenticationResponseToEntity(): AuthenticationSuccessEntity {
    return AuthenticationSuccessEntity(this.success)
}