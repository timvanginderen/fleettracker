package be.tim.fleettracker

import android.location.Location
import androidx.lifecycle.MutableLiveData

/**
 * Returns the `location` object as a human readable string.
 */
fun Location?.toText():String {
    return if (this != null) {
        "($latitude, $longitude)"
    } else {
        "Unknown location"
    }
}

/**
 * Extension function that initialises MutableLiveData
 */
fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }
