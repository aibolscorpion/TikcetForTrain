package kz.almaty.divTech.ui.search.trainDetail

import kz.almaty.divTech.data.searchTrains.Train

interface TrainClickedListener {
    fun trainClicked(train: Train)
}