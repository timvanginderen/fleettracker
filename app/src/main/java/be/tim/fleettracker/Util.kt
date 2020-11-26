package be.tim.fleettracker

import android.location.Location
import androidx.lifecycle.MutableLiveData
import be.tim.fleettracker.data.local.entity.LocationEntity

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
 * Returns the `location` object as a `LocationEntity`
 */
fun Location.toLocationEntity() = LocationEntity(
        0,provider, accuracy, latitude, longitude, speed, time, bearing
)

/**
 * Extension function that initialises MutableLiveData
 */
fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }
