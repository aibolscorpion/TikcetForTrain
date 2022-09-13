package kz.almaty.divTech.data.buyTicket

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Ticket(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @SerializedName("depStationCode") val depStationCode: String?,
    @SerializedName("arrStationCode") val arrStationCode: String?,
    @SerializedName("depDateTime") val depDateTime: String?,
    @SerializedName("arrDateTime") val arrDateTime: String?,
    @SerializedName("trainNumber") var trainNumber: String?,
    @SerializedName("carNumber") var carNumber: String?,
    @SerializedName("placeNumber") var placeNumber: Int?,
    @Embedded
    @SerializedName("passenger") var passenger: Passenger? )

