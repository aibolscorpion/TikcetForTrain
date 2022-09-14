package kz.almaty.divTech.ui.search.searchTrain

import kz.almaty.divTech.data.searchTrains.Trains

open class Event<out T>(private val content: Trains?) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): Trains? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): Trains? = content
}