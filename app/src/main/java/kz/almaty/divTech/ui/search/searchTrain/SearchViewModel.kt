package kz.almaty.divTech.ui.search.searchTrain

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kz.almaty.divTech.data.Constants
import kz.almaty.divTech.utils.SessionManager
import kz.almaty.divTech.api.Retrofit
import kz.almaty.divTech.data.authentication.Authentication
import kz.almaty.divTech.data.authentication.Token
import kz.almaty.divTech.data.buyTicket.BuyTicket
import kz.almaty.divTech.data.searchTrains.SearchTrains
import kz.almaty.divTech.data.searchTrains.Trains
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    val sessionManager = SessionManager()
    val trainsMutableLiveData =  MutableLiveData<Trains>()
    val progressBarLiveData = MutableLiveData<Boolean>()
    fun authenticate(){
        Retrofit.getApi(Constants.BASE_URL).authenticate(Authentication(Constants.APP_KEY, Constants.APP_SECRET))
            .enqueue(object :
                Callback<Token> {
                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    val authResponse = response.body()
                    if(response.isSuccessful){
                        sessionManager.saveAuthToken(authResponse!!.token!!)
                    }
                }

                override fun onFailure(call: Call<Token>, t: Throwable) {
                    Toast.makeText(getApplication<Application>().applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
                }

            })
    }
    fun getListOfTrains(searchTrains: SearchTrains){
        Retrofit.getApi(Constants.BASE_URL)
            .getTrainList(searchTrains).enqueue(object : Callback<Trains> {
                override fun onResponse(call: Call<Trains>, response: Response<Trains>) {
                    progressBarLiveData.postValue(false)
                    val trainsResponse = response.body()
                    if(response.isSuccessful){
                        trainsMutableLiveData.postValue(trainsResponse!!)
                    }
                }

                override fun onFailure(call: Call<Trains>, t: Throwable) {
                    progressBarLiveData.postValue(false)
                }

            })
    }
}