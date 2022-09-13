package kz.almaty.divTech.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Retrofit {
    private var retrofit : Retrofit? = null
    private lateinit var apiService: ApiService

    fun getApi(baseUrl: String): ApiService {
        apiService = getRetrofitClient(baseUrl).create(ApiService::class.java)
        return apiService
    }

    private fun getRetrofitClient(baseUrl : String) : Retrofit{

        val okHttpClient : OkHttpClient = OkHttpClient.Builder()
            .readTimeout((60*2).toLong(), TimeUnit.SECONDS)
            .connectTimeout((60*2).toLong(), TimeUnit.SECONDS)
            .writeTimeout((60*2).toLong(), TimeUnit.SECONDS)
            .addInterceptor(AuthInterceptor())
            .build()

            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        return retrofit!!
    }

}