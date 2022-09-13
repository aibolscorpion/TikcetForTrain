package kz.almaty.divTech.data.searchTrains

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Train(
    @SerializedName("number") val number: String?,
    @SerializedName("departureDatetime") val departureDatetime : String?,
    @SerializedName("arrivalDatetime") var arrivalDatetime   : String?,
    @SerializedName("cars") var cars: ArrayList<Car> = arrayListOf()
) : Parcelable
