package be.tim.fleettracker.di.module

import be.tim.fleettracker.ui.home.HomeFragment
import be.tim.fleettracker.ui.locations.LocationListFragment
import be.tim.fleettracker.ui.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeLocationListFragment(): LocationListFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

}