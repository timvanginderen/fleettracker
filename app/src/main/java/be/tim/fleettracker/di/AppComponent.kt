package be.tim.fleettracker.di

import android.app.Application
import be.tim.fleettracker.AppController
import be.tim.fleettracker.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApiModule::class, DbModule::class, LocationModule::class,
        ViewModelModule::class, AndroidSupportInjectionModule::class,
        ActivityModule::class, ServiceModule::class, FragmentModule::class]
)

interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun apiModule(apiModule: ApiModule): Builder

        @BindsInstance
        fun dbModule(dbModule: DbModule): Builder

        @BindsInstance
        fun locationModule(locationModule: LocationModule): Builder

        fun build(): AppComponent
    }

    fun inject(appController: AppController)
}
