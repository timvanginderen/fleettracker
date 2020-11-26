package be.tim.fleettracker.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.tim.fleettracker.default
import be.tim.fleettracker.prefs.AuthPrefManager
import be.tim.fleettracker.prefs.LocationPrefManager
import javax.inject.Inject

class HomeViewModel @Inject constructor(private var authPrefManager: AuthPrefManager,
                                        private var locationPrefManager: LocationPrefManager): ViewModel() {

    val logResults: MutableLiveData<String> = MutableLiveData<String>().default("")

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