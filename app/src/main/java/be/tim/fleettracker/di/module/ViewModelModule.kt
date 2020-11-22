package be.tim.fleettracker.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.tim.fleettracker.ui.locations.LocationListViewModel
import be.tim.fleettracker.factory.ViewModelFactory
import be.tim.fleettracker.ui.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap



@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    /*
     * This method basically says
     * inject this object into a Map using the @IntoMap annotation,
     * with the  ListViewModel.class as key,
     * and a Provider that will build a ListViewModel
     * object.
     *
     * */

    @Binds
    @IntoMap
    @ViewModelKey(LocationListViewModel::class)
    protected abstract fun locationListViewModel(locationListViewModel: LocationListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    protected abstract fun loginViewModel(loginViewModel: LoginViewModel): ViewModel
}