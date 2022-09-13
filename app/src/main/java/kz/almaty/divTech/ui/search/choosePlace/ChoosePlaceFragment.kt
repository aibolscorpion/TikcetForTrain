package kz.almaty.divTech.ui.search.choosePlace

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.almaty.divTech.R
import kz.almaty.divTech.data.buyTicket.BuyTicket
import kz.almaty.divTech.data.searchTrains.Car
import kz.almaty.divTech.data.searchTrains.Train
import kz.almaty.divTech.utils.DateFormatter

class ChoosePlaceFragment : Fragment(), CarClickedListener{
    lateinit var buyTicket: BuyTicket
    lateinit var train : Train
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,): View? {
        val arguments = ChoosePlaceFragmentArgs.fromBundle(requireArguments())
        train = arguments.train
        buyTicket = arguments.buyTicket

        val view = inflater.inflate(R.layout.fragment_choose_place, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.carsRecyclerView)
        val trainNumberTextView = view.findViewById<TextView>(R.id.trainNumberTextView)
        val departureDateTextView: TextView = view.findViewById(R.id.departureDateTextView)
        val departureTimeTextView: TextView = view.findViewById(R.id.departureTimeTextView)
        val arrivalDateTextView: TextView = view.findViewById(R.id.arrivalDateTextView)
        val arrivalTimeTextView: TextView = view.findViewById(R.id.arrivalTimeTextView)

        trainNumberTextView.text = getString(R.string.train_number, train.number)

        val formattedDepartureDateTime = DateFormatter.formatDateAndTime(train.departureDatetime)
        departureDateTextView.text = formattedDepartureDateTime[0]
        departureTimeTextView.text = formattedDepartureDateTime[1]

        val formattedArrivalDateTime = DateFormatter.formatDateAndTime(train.arrivalDatetime)
        arrivalDateTextView.text = formattedArrivalDateTime[0]
        arrivalTimeTextView.text = formattedArrivalDateTime[1]

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = CarAdapter(this, train.cars)

        return view
    }

    override fun carClicked(carNumber: String, placeNumber : Int) {
        buyTicket.carNumber = carNumber
        buyTicket.placeNumber = placeNumber
        val action = ChoosePlaceFragmentDirections.actionChoosePlaceFragmentToBuyTicket(buyTicket, train)
        findNavController().navigate(action)
    }

}