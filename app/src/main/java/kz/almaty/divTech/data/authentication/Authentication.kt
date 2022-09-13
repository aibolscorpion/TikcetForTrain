package kz.almaty.divTech.data.authentication

import com.google.gson.annotations.SerializedName

data class Authentication(
    @SerializedName("app_key") val app_key: String?,
    @SerializedName("app_secret") val app_secret: String?
)
