package be.tim.fleettracker.ui.home

import androidx.lifecycle.ViewModel
import be.tim.fleettracker.prefs.AuthPrefManager
import javax.inject.Inject

class HomeViewModel @Inject constructor(authPrefManager: AuthPrefManager): ViewModel() {

    private val prefManager: AuthPrefManager = authPrefManager

    fun isLoggedIn() : Boolean {
        return prefManager.isLoggedIn()
    }
}