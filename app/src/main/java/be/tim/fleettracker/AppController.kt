package be.tim.fleettracker

import android.app.Activity
import android.app.Application
import android.app.Service
import be.tim.fleettracker.di.DaggerAppComponent
import be.tim.fleettracker.di.module.ApiModule
import be.tim.fleettracker.di.module.DbModule
import be.tim.fleettracker.di.module.LocationModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import javax.inject.Inject

/*
 * we use our AppComponent (now prefixed with Dagger)
 * to inject our Application class.
 * This way a DispatchingAndroidInjector is injected which is
 * then returned when an injector for an activity is requested.
 * */
class AppController : Application(), HasActivityInjector, HasServiceInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    @Inject
    lateinit var dispatchingServiceAndroidInjector: DispatchingAndroidInjector<Service>

    override fun serviceInjector(): DispatchingAndroidInjector<Service> {
        return dispatchingServiceAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .apiModule(ApiModule())
            .dbModule(DbModule())
            .locationModule(LocationModule())
            .build()
            .inject(this)
    }


}