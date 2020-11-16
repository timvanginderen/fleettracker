package be.tim.fleettracker.data.remote

import be.tim.fleettracker.prefs.AuthPrefManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(authPrefManager: AuthPrefManager) : Interceptor {

    private val prefManager = authPrefManager

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        prefManager.getAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}