package kz.almaty.divTech.ui.search.trainDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.almaty.divTech.R
import kz.almaty.divTech.data.buyTicket.BuyTicket
import kz.almaty.divTech.data.searchTrains.Train

class TrainDetailFragment : Fragment(), TrainClickedListener {
    lateinit var buyTicket : BuyTicket

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,): View? {
        val view =  inflater.inflate(R.layout.fragment_train_detail, container, false)
        val recyclerView  = view.findViewById<RecyclerView>(R.id.trainRecyclerView)

        val arguments = TrainDetailFragmentArgs.fromBundle(requireArguments())
        val trains = arguments.trains
        buyTicket = arguments.buyTicket
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = CustomAdapter(this, trains.trains)
        return view
    }

    override fun trainClicked(train: Train) {
        buyTicket.trainNumber = train.number
        val action = TrainDetailFragmentDirections.actionTrainDetailFragmentToChoosePlaceFragment(train,buyTicket)
        findNavController().navigate(action)
    }

}