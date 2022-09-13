package kz.almaty.divTech.data.buyTicket

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Passenger(
    @SerializedName("firstName") var firstName: String?,
    @SerializedName("lastName") var lastName: String?
) : Parcelable
