package kz.almaty.divTech.room

import androidx.room.*
import kz.almaty.divTech.data.buyTicket.BuyTicket
import kz.almaty.divTech.data.buyTicket.Ticket


@Dao
interface TicketDao {
    @get:Query("SELECT * FROM ticket")
    val all: List<Ticket>?

    @Insert
    fun insert(ticket: Ticket)
}