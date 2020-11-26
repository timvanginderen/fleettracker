package be.tim.fleettracker.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.tim.fleettracker.data.Resource
import be.tim.fleettracker.data.local.dao.LocationDao
import be.tim.fleettracker.data.local.entity.LocationEntity
import be.tim.fleettracker.data.remote.ApiService
import be.tim.fleettracker.data.repository.LocationRepository
import be.tim.fleettracker.default
import be.tim.fleettracker.prefs.AuthPrefManager
import be.tim.fleettracker.prefs.LocationPrefManager
import javax.inject.Inject

class HomeViewModel @Inject constructor(private var authPrefManager: AuthPrefManager,
                                        private var locationPrefManager: LocationPrefManager,
                                        locationDao: LocationDao,
                                        apiService: ApiService): ViewModel() {


    private val locationRepository: LocationRepository = LocationRepository(locationDao, apiService)
    private val locationListLiveData = MutableLiveData<Resource<List<LocationEntity>>>()

    val logResults: MutableLiveData<String> = MutableLiveData<String>().default("")

    fun getLocationsLiveData() = locationListLiveData

    fun loadLocations() {
        locationRepository.loadLocations()
            .subscribe { resource -> getLocationsLiveData().postValue(resource) }
    }

    fun isLoggedIn() : Boolean {
        return authPrefManager.isLoggedIn()
    }

    fun getLocationTrackingPref() : Boolean {
        return locationPrefManager.getLocationTrackingPref()
    }

    fun addToLogResults(output:String) {
        logResults.postValue("$output\n${logResults.value}")
    }

}