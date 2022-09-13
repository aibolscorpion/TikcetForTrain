package kz.almaty.divTech.ui.search.choosePlace

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kz.almaty.divTech.R
import kz.almaty.divTech.data.searchTrains.Car

class CarAdapter(private val carClickedListener: CarClickedListener, private val carList: List<Car>?) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        var placeNumber = 0
        val context = holder.itemView.context
        val item = carList?.get(position)

        holder.carNumberTextView.text = context.getString(R.string.car_number,item?.number)
        if(item?.type == 1){
            holder.coupeOrReservedSeatTextView.text = context.getString(R.string.coupe)
        }else if(item?.type == 2){
            holder.coupeOrReservedSeatTextView.text = context.getString(R.string.reserved_seat)
        }
        item?.places?.forEach { place ->
            val radioButton = RadioButton(context)
            radioButton.text = place.toString()
            holder.placesRadioGroup.addView(radioButton)
        }
        holder.placesRadioGroup.setOnCheckedChangeListener { radioGroup, buttonId ->
            val radioButton = radioGroup.findViewById<RadioButton>(buttonId)
            placeNumber = radioButton.text.toString().toInt()
        }

        holder.chooseCarButton.setOnClickListener {
            if(placeNumber == 0){
                Toast.makeText(context, context.getString(R.string.choose_place_to_continue), Toast.LENGTH_SHORT).show()
            }else{
                carClickedListener.carClicked(item?.number!!, placeNumber) }
            }

    }

    override fun getItemCount(): Int {
        if(carList == null){
            return 0
        }else{
            return carList.size
        }
    }

    inner class CarViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
        val coupeOrReservedSeatTextView: TextView = itemView.findViewById(R.id.coupeOrReservedSeatTextView)
        val chooseCarButton : Button = itemView.findViewById(R.id.chooseCarButton)
        val placesRadioGroup : RadioGroup = itemView.findViewById(R.id.placesRadioGroup)
        val carNumberTextView : TextView = itemView.findViewById(R.id.carNumberTextView)

    }
}