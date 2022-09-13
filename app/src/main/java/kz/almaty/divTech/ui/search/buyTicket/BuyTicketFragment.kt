package kz.almaty.divTech.ui.search.buyTicket

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import kz.almaty.divTech.R
import kz.almaty.divTech.data.buyTicket.Passanger
import kz.almaty.divTech.data.buyTicket.Ticket
import kz.almaty.divTech.data.searchTrains.Train

class BuyTicketFragment : Fragment() {

    private lateinit var viewModel: BuyTicketViewModel
    lateinit var train : Train
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(this)[BuyTicketViewModel::class.java]
        val arguments = BuyTicketFragmentArgs.fromBundle(requireArguments())
        val buyTicket = arguments.buyTicket
        train = arguments.train


        val view = inflater.inflate(R.layout.fragment_buy_ticket, container, false)
        val nameEditText = view.findViewById<EditText>(R.id.nameEditText)
        val surnameEditText = view.findViewById<EditText>(R.id.surnameEditText)
        val buyTicketButton = view.findViewById<AppCompatButton>(R.id.buyTicketButton)

        viewModel.orderIdMutableLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), getString(R.string.ticket_was_purchased_successfully, it), Toast.LENGTH_SHORT).show()
            val action = BuyTicketFragmentDirections.actionNavigationBuyTicketToNavigationTickets()
            findNavController().navigate(action)
        }

        buyTicketButton.setOnClickListener {
            if(nameEditText.text.isEmpty() || surnameEditText.text.isEmpty()){
                Toast.makeText(requireContext(), R.string.fill_all_fields, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            buyTicket.passenger = Passanger(nameEditText.text.toString(), surnameEditText.text.toString())
            viewModel.buyTicket(buyTicket)

            val ticket = Ticket(depStationCode =  buyTicket.depStationCode, arrStationCode = buyTicket.arrStationCode, depDateTime= train.departureDatetime, arrDateTime = train.arrivalDatetime,
                trainNumber=  buyTicket.trainNumber, carNumber = buyTicket.carNumber, placeNumber = buyTicket.placeNumber, passenger = buyTicket.passenger)
            viewModel.addTicketToDB(ticket)
        }
        return view
    }


}