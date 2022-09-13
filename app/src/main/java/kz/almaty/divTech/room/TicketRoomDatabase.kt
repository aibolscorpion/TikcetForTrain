package kz.almaty.divTech.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kz.almaty.divTech.data.buyTicket.Ticket

@Database(entities = [Ticket::class], version = 1, exportSchema = false)
 abstract class TicketRoomDatabase : RoomDatabase() {

    abstract fun ticketDao() : TicketDao?
    companion object {
        private var INSTANCE: TicketRoomDatabase? = null


        fun getDatabase(context: Context): TicketRoomDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    TicketRoomDatabase::class.java,
                    "ticket_database"
                ).allowMainThreadQueries().build()
            }
            return INSTANCE as TicketRoomDatabase
        }
    }
}