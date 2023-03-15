package com.example.ltech.data.models.remote.authentication

import com.google.gson.annotations.SerializedName

data class AuthPhoneMaskResponse(
    @SerializedName("phoneMask")
    val phoneMask: String
)