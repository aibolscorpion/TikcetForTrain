package kz.almaty.divTech.data.buyTicket

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class BuyTicket(
    @SerializedName("depStationCode") val depStationCode: String,
    @SerializedName("arrStationCode") val arrStationCode: String?,
    @SerializedName("depDate") val depDate: String?,
    @SerializedName("trainNumber") var trainNumber: String?,
    @SerializedName("carNumber") var carNumber: String?,
    @SerializedName("placeNumber") var placeNumber: Int?,
    @Embedded
    @SerializedName("passenger") var passenger: Passanger?) : Parcelable
