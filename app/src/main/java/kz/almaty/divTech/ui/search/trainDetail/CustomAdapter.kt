package kz.almaty.divTech.ui.search.trainDetail

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import kz.almaty.divTech.R
import kz.almaty.divTech.data.searchTrains.Train
import kz.almaty.divTech.utils.DateFormatter

class CustomAdapter(private val trainListener: TrainClickedListener, private val trainList: List<Train>?) : RecyclerView.Adapter<CustomAdapter.TrainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_train, parent, false)
        return TrainViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
        var coupeQuantity = 0
        var reservedSeatQuantity = 0
        val train = trainList?.get(position)
        val formattedDepartureDateTime = DateFormatter.formatDateAndTime(train?.departureDatetime)
        holder.departureDateTextView.text = formattedDepartureDateTime[0]
        holder.departureTimeTextView.text = formattedDepartureDateTime[1]

        val formattedArrivalDateTime = DateFormatter.formatDateAndTime(train?.arrivalDatetime)
        holder.arrivalDateTextView.text = formattedArrivalDateTime[0]
        holder.arrivalTimeTextView.text = formattedArrivalDateTime[1]

        holder.trainNumberTextView.text = holder.itemView.context.getString(R.string.train_number, train?.number)
        train?.cars?.forEach { car ->
            if(car.type == 1){
                coupeQuantity += car.places?.size!!
                holder.coupeTextView.text = coupeQuantity.toString()
            }else if(car.type == 2){
                reservedSeatQuantity += car.places?.size!!
                holder.reservedSeatTextView.text = reservedSeatQuantity.toString()
            }
        }
        holder.chooseTrainButton.setOnClickListener {
            trainListener.trainClicked(train!!)
        }

    }

    override fun getItemCount(): Int {
        if(trainList == null){
            return 0
        }else{
            return trainList.size
        }
    }

    inner class TrainViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
         val departureDateTextView: TextView = itemView.findViewById(R.id.departureDateTextView)
        val departureTimeTextView: TextView = itemView.findViewById(R.id.departureTimeTextView)
         val arrivalDateTextView: TextView = itemView.findViewById(R.id.arrivalDateTextView)
         val arrivalTimeTextView: TextView = itemView.findViewById(R.id.arrivalTimeTextView)
         val trainNumberTextView: TextView = itemView.findViewById(R.id.trainNumberTextView)
         val coupeTextView : TextView = itemView.findViewById(R.id.coupeTextView)
         val reservedSeatTextView : TextView = itemView.findViewById(R.id.reservedSeatTextView)
         val chooseTrainButton: AppCompatButton = itemView.findViewById(R.id.chooseTrainButton)

    }
}