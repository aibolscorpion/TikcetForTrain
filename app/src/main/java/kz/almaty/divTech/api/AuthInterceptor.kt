package kz.almaty.divTech.api

import android.content.Context
import kz.almaty.divTech.MyApplication
import kz.almaty.divTech.utils.SessionManager
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor() : Interceptor {
    private val sessionManager = SessionManager()

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}