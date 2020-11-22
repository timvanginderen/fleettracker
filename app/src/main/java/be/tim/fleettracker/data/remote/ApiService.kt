package be.tim.fleettracker.data.remote

import be.tim.fleettracker.Constants
import be.tim.fleettracker.data.local.entity.LocationEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET(Constants.TODOS_ENDPOINT)
    fun getLocations() : Observable<List<LocationEntity>>

    @POST(Constants.LOGIN_ENDPOINT)
    fun login() : Observable<LoginResponse>
}