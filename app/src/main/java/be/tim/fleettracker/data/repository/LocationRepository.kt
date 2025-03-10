package be.tim.fleettracker.data.repository

import android.util.Log
import be.tim.fleettracker.data.NetworkBoundResource
import be.tim.fleettracker.data.Resource
import be.tim.fleettracker.data.local.dao.LocationDao
import be.tim.fleettracker.data.local.entity.LocationEntity
import be.tim.fleettracker.data.remote.ApiService
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Singleton

/*
 * One of the first things we do in the Repository class
 * is to make it a Singleton.
 * */

@Singleton
class LocationRepository(
        private val locationDao: LocationDao,
        private val locationApiService: ApiService
) {

    private val TAG = LocationRepository::class.qualifiedName

    /*
     * NetworkBoundResource is part of the Android architecture
     * components. You will notice that this is a modified version of 
     * that class. That class is based on LiveData but here we are 
     * using Observable from RxJava.
     * 
     * So basically we fetch data from server, store it locally
     * and then fetch data from local and update the UI with 
     * this data.
     * 
     * */
    fun loadLocations(): Observable<Resource<List<LocationEntity>>> {
        return object : NetworkBoundResource<List<LocationEntity>, List<LocationEntity>>() {

            override fun saveCallResult(item: List<LocationEntity>) {
                Log.d(TAG, "Insert ${item.size} locations into db")
                locationDao.insertLocations(item)
            }

            override fun shouldFetch(): Boolean {
                // TODO: 26-Nov-20  implement
                return false
            }

            override fun loadFromDb(): Flowable<List<LocationEntity>> {
                val locationEntities = locationDao.getLocations()
                return if (locationEntities == null || locationEntities.isEmpty()) {
                    Flowable.empty()
                } else Flowable.just(locationEntities)
            }

            override fun createCall(): Observable<Resource<List<LocationEntity>>> {
                return locationApiService.getLocations()
                    .flatMap { response ->
                        Observable.just(
                                if (response == null) Resource.error("", emptyList<LocationEntity>())
                                else Resource.success(response)
                        )
                    }
            }
        }.getAsObservable()
    }
}