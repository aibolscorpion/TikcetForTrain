package kz.almaty.divTech.ui.search.searchTrain

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kz.almaty.divTech.databinding.FragmentSearchBinding
import kz.almaty.divTech.R
import kz.almaty.divTech.data.buyTicket.BuyTicket
import kz.almaty.divTech.data.searchTrains.SearchTrains
import kz.almaty.divTech.utils.DateFormatter

class SearchFragment : Fragment(){
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var root : View
    private var departureCity : String?= null
    private var arrivalCity : String? = null
    private var departureDate : String? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        root = binding.root

        setupView()

        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setupView(){
        val searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        searchViewModel.authenticate()

        val findTicketsButton = root.findViewById<AppCompatButton>(R.id.findTicketsButton)
        val departureCityAutoCompleteTV = root.findViewById<AutoCompleteTextView>(R.id.departureCityAutoCompleteTV)
        val arrivalCityAutoCompleteTV = root.findViewById<AutoCompleteTextView>(R.id.arrivalCityAutoCompleteTV)
        val departureDateEditText = root.findViewById<EditText>(R.id.departureDateEditText)
        val findTicketsProgressBar = root.findViewById<ProgressBar>(R.id.findTicketsProgressBar)

        val cities = resources.getStringArray(R.array.cities)
        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, cities)
        departureCityAutoCompleteTV.setAdapter(arrayAdapter )
        arrivalCityAutoCompleteTV.setAdapter(arrayAdapter)

        departureCityAutoCompleteTV.onItemClickListener = ( AdapterView.OnItemClickListener { parent, view, position, long ->
            departureCity = parent?.getItemAtPosition(position).toString()
        })
        arrivalCityAutoCompleteTV.onItemClickListener = ( AdapterView.OnItemClickListener { parent, view, position, long ->
            arrivalCity = parent?.getItemAtPosition(position).toString()
        })

        departureDateEditText.setOnClickListener {
            val datePickerDialog = DatePickerDialog(requireContext())
            datePickerDialog.setOnDateSetListener { _, year, month, dayOfMonth ->
                departureDate = "$dayOfMonth." + (month + 1) + ".$year"
                departureDateEditText.setText(DateFormatter.formatDate(departureDate!!))
            }
            datePickerDialog.show()
        }


        searchViewModel.trainsMutableLiveData.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
            val buyTicket = BuyTicket(departureCity!!, arrivalCity, DateFormatter.formatDateForRequest(departureDate!!), null, null, null, null)
            val action = SearchFragmentDirections.actionNavigationSearchToTrainDetailFragment(it, buyTicket)
            findNavController().navigate(action)
                }
        }
        searchViewModel.progressBarLiveData.observe(viewLifecycleOwner){
            findTicketsProgressBar.visibility = View.GONE
        }

        findTicketsButton.setOnClickListener {
            if(departureCity == null || arrivalCity == null || departureDate == null){
                Toast.makeText(requireContext(), getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            findTicketsProgressBar.visibility = View.VISIBLE
            searchViewModel.getListOfTrains(SearchTrains(departureCity, arrivalCity , departureDate))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}