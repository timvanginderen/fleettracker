package be.tim.fleettracker.di.module

import be.tim.fleettracker.ui.locations.LocationListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeLocationListFragment(): LocationListFragment

}