package be.tim.fleettracker.ui.home

import androidx.lifecycle.ViewModel
import be.tim.fleettracker.prefs.AuthPrefManager
import be.tim.fleettracker.prefs.LocationPrefManager
import javax.inject.Inject

class HomeViewModel @Inject constructor(private var authPrefManager: AuthPrefManager,
                                        private var locationPrefManager: LocationPrefManager): ViewModel() {

    fun isLoggedIn() : Boolean {
        return authPrefManager.isLoggedIn()
    }

    fun getLocationTrackingPref() : Boolean {
        return locationPrefManager.getLocationTrackingPref()
    }

    fun saveLocationTrackingPref() {

    }
}