package kz.almaty.divTech.data.authentication

import com.google.gson.annotations.SerializedName

data class Token (
    @SerializedName("token")  val token : String?
)