package kz.almaty.divTech.ui.tickets

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kz.almaty.divTech.MyApplication
import kz.almaty.divTech.data.buyTicket.Ticket
import kz.almaty.divTech.room.TicketRoomDatabase

class TicketsViewModel : ViewModel() {

    val allTicketsMutableLiveData = MutableLiveData<List<Ticket>>()

    fun getAllTicketsFromDB(){
        val ticketDao = TicketRoomDatabase.getDatabase(MyApplication.appContext).ticketDao()
        allTicketsMutableLiveData.value = ticketDao?.all
    }
}