package be.tim.fleettracker.di.module

import be.tim.fleettracker.ForegroundOnlyLocationService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceModule {
    @ContributesAndroidInjector
    abstract fun contributeLocationService(): ForegroundOnlyLocationService
}