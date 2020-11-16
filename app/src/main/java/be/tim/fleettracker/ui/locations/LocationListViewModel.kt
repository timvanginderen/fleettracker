package be.tim.fleettracker.ui.locations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.tim.fleettracker.data.Resource
import be.tim.fleettracker.data.repository.LocationRepository
import be.tim.fleettracker.data.local.dao.LocationDao
import be.tim.fleettracker.data.local.entity.LocationEntity
import be.tim.fleettracker.data.remote.ApiService
import javax.inject.Inject

class LocationListViewModel @Inject constructor(apiService: ApiService, locationDao: LocationDao) : ViewModel() {

    /* You can see we are initialising the Repository class here */
    private val locationRepository: LocationRepository = LocationRepository(locationDao, apiService)

    /* We are using LiveData to update the UI with the data changes.
    */
    private val locationListLiveData = MutableLiveData<Resource<List<LocationEntity>>>()

    /*
   * Method called by UI to fetch movies list
   * */
    fun loadMore() {
        locationRepository.loadLocations()
            .subscribe { resource -> getLocationsLiveData().postValue(resource) }
    }

    /*
     * LiveData observed by the UI
     * */
    fun getLocationsLiveData() = locationListLiveData
}