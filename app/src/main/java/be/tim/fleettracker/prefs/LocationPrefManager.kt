package be.tim.fleettracker.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import be.tim.fleettracker.R

const val KEY_FOREGROUND_ENABLED = "tracking_foreground_location"

class LocationPrefManager(context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)

    /**
     * Returns true if requesting location updates, otherwise returns false.
     */
    fun getLocationTrackingPref(): Boolean =
        prefs.getBoolean(KEY_FOREGROUND_ENABLED, false)

    /**
     * Stores the location updates state in SharedPreferences.
     * @param requestingLocationUpdates The location updates state.
     */
    fun saveLocationTrackingPref(requestingLocationUpdates: Boolean) =
        prefs.edit {
            putBoolean(KEY_FOREGROUND_ENABLED, requestingLocationUpdates)
        }

}