package kz.almaty.divTech.data.searchTrains

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Trains(
    @SerializedName("trains" ) val trains : ArrayList<Train> = arrayListOf()
) : Parcelable
