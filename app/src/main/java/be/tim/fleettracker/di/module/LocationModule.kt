package be.tim.fleettracker.di.module

import android.app.Application
import be.tim.fleettracker.prefs.LocationPrefManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocationModule {

    @Provides
    @Singleton
    internal fun provideLocationPrefManager(application: Application): LocationPrefManager {
        return LocationPrefManager(application.applicationContext)
    }
}