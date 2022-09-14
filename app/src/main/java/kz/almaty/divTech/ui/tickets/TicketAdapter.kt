package kz.almaty.divTech.ui.tickets

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kz.almaty.divTech.R
import kz.almaty.divTech.data.buyTicket.Ticket
import kz.almaty.divTech.utils.DateFormatter

class TicketAdapter(private val ticketList: List<Ticket>?) : RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ticket, parent, false)
        return TicketViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val ticket = ticketList?.get(position)

        holder.trainNumberTextView.text =  ticket?.trainNumber
        holder.carNumberTextView.text =  ticket?.carNumber
        holder.placeNumberTextView.text =  ticket?.placeNumber.toString()

        val formattedDepartureDateTime = DateFormatter.formatDateAndTime(ticket?.depDateTime)
        holder.departureDateTextView.text = formattedDepartureDateTime[0]
        holder.departureTimeTextView.text = formattedDepartureDateTime[1]

        val formattedArrivalDateTime = DateFormatter.formatDateAndTime(ticket?.arrDateTime)
        holder.arrivalDateTextView.text = formattedArrivalDateTime[0]
        holder.arrivalTimeTextView.text = formattedArrivalDateTime[1]

        holder.departureCityTextView.text = ticket?.depStationCode
        holder.arrivalCityTextView.text = ticket?.arrStationCode

        holder.nameTextView.text = ticket?.passenger?.firstName
        holder.surnameTextView.text = ticket?.passenger?.lastName

    }

    override fun getItemCount(): Int {
        if(ticketList == null){
            return 0
        }else{
            return ticketList.size
        }
    }

    inner class TicketViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
        val trainNumberTextView: TextView = itemView.findViewById(R.id.trainNumberTextView)
        val carNumberTextView: TextView = itemView.findViewById(R.id.carNumberTextView)
        val placeNumberTextView: TextView = itemView.findViewById(R.id.placeNumberTextView)
        val departureDateTextView: TextView = itemView.findViewById(R.id.departureDateTextView)
        val departureTimeTextView: TextView = itemView.findViewById(R.id.departureTimeTextView)
        val arrivalDateTextView: TextView = itemView.findViewById(R.id.arrivalDateTextView)
        val arrivalTimeTextView: TextView = itemView.findViewById(R.id.arrivalTimeTextView)
        val departureCityTextView: TextView = itemView.findViewById(R.id.departureCityTextView)
        val arrivalCityTextView: TextView = itemView.findViewById(R.id.arrivalCityTextView)
        val nameTextView : TextView = itemView.findViewById(R.id.nameTextView)
        val surnameTextView : TextView = itemView.findViewById(R.id.surnameTextView)

    }
}