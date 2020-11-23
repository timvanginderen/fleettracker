package be.tim.fleettracker.data.repository

import android.util.Log
import be.tim.fleettracker.data.NetworkBoundResource
import be.tim.fleettracker.data.Resource
import be.tim.fleettracker.data.remote.ApiService
import be.tim.fleettracker.data.remote.LoginResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Singleton

@Singleton
class LoginRepository(
        private val apiService: ApiService
) {

    private val TAG = LoginRepository::class.qualifiedName

    fun login(): Observable<Resource<LoginResponse>> {
        return object : NetworkBoundResource<LoginResponse, LoginResponse>() {

            override fun saveCallResult(item: LoginResponse) {
//                Log.d(TAG, "Insert ${item.size} todos into db")
//                locationDao.insertLocations(item)
                // TODO: 22-Nov-20  save token in shared prefs
                Log.d(TAG, "Saving token: ${item.token}")
            }

            override fun shouldFetch(): Boolean {
                return true
            }

            override fun loadFromDb(): Flowable<LoginResponse> {
//                val locationEntities = locationDao.getLocations()
//                return if (locationEntities == null || locationEntities.isEmpty()) {
//                    Flowable.empty()
//                } else Flowable.just(locationEntities)
                return Flowable.empty()
            }

            override fun createCall(): Observable<Resource<LoginResponse>> {
//                return locationApiService.getLocations()
//                        .flatMap { response ->
//                            Observable.just(
//                                    if (response == null) Resource.error("", emptyList<LocationEntity>())
//                                    else Resource.success(response)
//                            )
//                        }
                return apiService.login().flatMap { response -> Observable.just(
                        Resource.success(response)
                ).onErrorReturnItem(
                        Resource.error("observer error", LoginResponse()))}
//                        .onErrorReturn { t: Throwable -> Log.d(TAG, t.message.toString()) }
                        .doOnError { t: Throwable? -> Log.d(TAG, t!!.message.toString())
                        }
            }
        }.getAsObservable()
    }
}