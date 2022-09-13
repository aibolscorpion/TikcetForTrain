package kz.almaty.divTech.ui.search.buyTicket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kz.almaty.divTech.MyApplication
import kz.almaty.divTech.api.Retrofit
import kz.almaty.divTech.data.Constants
import kz.almaty.divTech.data.buyTicket.BuyTicket
import kz.almaty.divTech.data.buyTicket.Order
import kz.almaty.divTech.data.buyTicket.Ticket
import kz.almaty.divTech.room.TicketRoomDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuyTicketViewModel : ViewModel() {
    val orderIdMutableLiveData =  MutableLiveData<Int>()

    fun buyTicket(buyTicket: BuyTicket){
        Retrofit.getApi(Constants.BASE_URL)
            .buyTicket(buyTicket).enqueue(object : Callback<Order> {
                override fun onResponse(call: Call<Order>, response: Response<Order>) {
                    val order = response.body()
                    if(response.isSuccessful){
                        orderIdMutableLiveData.postValue(order?.orderId!!)
                    }
                }

                override fun onFailure(call: Call<Order>, t: Throwable) {
                }

            })
    }

    fun addTicketToDB(ticket: Ticket){
        val ticketDao = TicketRoomDatabase.getDatabase(MyApplication.appContext).ticketDao()
        ticketDao?.insert(ticket)
    }

}