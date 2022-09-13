package kz.almaty.divTech.data.searchTrains

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car(
    @SerializedName("number") val number : String?,
    @SerializedName("type") val type   : Int?,
    @SerializedName("places") val places : ArrayList<Int>?
) : Parcelable
