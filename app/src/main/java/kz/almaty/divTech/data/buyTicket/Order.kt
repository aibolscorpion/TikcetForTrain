package kz.almaty.divTech.data.buyTicket

import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("orderId") val orderId: Int?
)
