package kz.almaty.divTech.data.searchTrains

import com.google.gson.annotations.SerializedName

data class SearchTrains(
    @SerializedName("depStationCode") val depStationCode: String?,
    @SerializedName("arrStationCode") val arrStationCode: String?,
    @SerializedName("depDate") val depDate: String?)
