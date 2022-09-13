package kz.almaty.divTech.ui.search.choosePlace

import kz.almaty.divTech.data.searchTrains.Car

interface CarClickedListener {
    fun carClicked(carNumber: String, placeNumber : Int)
}