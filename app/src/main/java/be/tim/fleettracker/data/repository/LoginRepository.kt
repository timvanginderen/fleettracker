package be.tim.fleettracker.data.repository

import android.util.Log
import be.tim.fleettracker.data.NetworkBoundResource
import be.tim.fleettracker.data.Resource
import be.tim.fleettracker.data.remote.ApiService
import be.tim.fleettracker.data.remote.LoginRequest
import be.tim.fleettracker.data.remote.LoginResponse
import be.tim.fleettracker.prefs.AuthPrefManager
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Singleton

@Singleton
class LoginRepository(
        private val apiService: ApiService,
        private val authPrefManager: AuthPrefManager
) {

    private val TAG = LoginRepository::class.qualifiedName

    fun login(email: String, password: String): Observable<Resource<LoginResponse>> {
        return object : NetworkBoundResource<LoginResponse, LoginResponse>() {

            override fun saveCallResult(item: LoginResponse) {
                // Save token in shared prefs
                Log.d(TAG, "Saving token: ${item.token}")
                if (item.token != null) {
                    authPrefManager.saveAuthToken(item.token!!)
                }
            }

            override fun shouldFetch(): Boolean {
                return true
            }

            override fun loadFromDb(): Flowable<LoginResponse> {
                val authToken = authPrefManager.getAuthToken()
                return Flowable.just(LoginResponse(authToken))
            }

            override fun createCall(): Observable<Resource<LoginResponse>> {
                val loginRequest = LoginRequest()
                loginRequest.email = email
                loginRequest.password = password

                return apiService.login(loginRequest).flatMap { response -> Observable.just(
                        Resource.success(response)
                ) }.doOnError { t: Throwable? -> Log.d(TAG, t!!.message.toString()) }
            }
        }.getAsObservable()
    }
}