package kz.almaty.divTech.ui.tickets

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.almaty.divTech.R
import kz.almaty.divTech.databinding.FragmentTicketsBinding
import kz.almaty.divTech.ui.search.buyTicket.BuyTicketViewModel

class TicketsFragment : Fragment() {

    private var _binding: FragmentTicketsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {
        val viewModel = ViewModelProvider(this)[TicketsViewModel::class.java]

        _binding = FragmentTicketsBinding.inflate(inflater, container, false)
        val view: View = binding.root
        val recyclerView = view.findViewById<RecyclerView>(R.id.ticketsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.allTicketsMutableLiveData.observe(viewLifecycleOwner) { list->
            recyclerView.adapter = TicketAdapter(list)
        }

        viewModel.getAllTicketsFromDB()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}