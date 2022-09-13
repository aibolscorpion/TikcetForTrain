package kz.almaty.divTech.api

import kz.almaty.divTech.data.authentication.Authentication
import kz.almaty.divTech.data.authentication.Token
import kz.almaty.divTech.data.buyTicket.BuyTicket
import kz.almaty.divTech.data.buyTicket.Order
import kz.almaty.divTech.data.searchTrains.SearchTrains
import kz.almaty.divTech.data.searchTrains.Trains
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("auth/get-token")
    fun authenticate(@Body authentication: Authentication) : Call<Token>

    @Headers("Content-Type: application/json")
    @POST("search-trains")
    fun getTrainList(@Body search: SearchTrains) : Call<Trains>

    @Headers("Content-Type: application/json")
    @POST("issue-ticket")
    fun buyTicket(@Body buyTicket: BuyTicket) : Call<Order>
}