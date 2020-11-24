package be.tim.fleettracker.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.tim.fleettracker.data.Resource
import be.tim.fleettracker.data.local.entity.LocationEntity
import be.tim.fleettracker.data.remote.ApiService
import be.tim.fleettracker.data.remote.LoginResponse
import be.tim.fleettracker.data.repository.LocationRepository
import be.tim.fleettracker.data.repository.LoginRepository
import be.tim.fleettracker.prefs.AuthPrefManager
import javax.inject.Inject

class LoginViewModel  @Inject constructor(apiService: ApiService, authPrefManager: AuthPrefManager) : ViewModel() {

    private val loginRepository: LoginRepository = LoginRepository(apiService, authPrefManager)

    private val loginLiveData = MutableLiveData<Resource<LoginResponse>>()


    fun login() {
//        locationRepository.loadLocations()
//                .subscribe { resource -> getLocationsLiveData().postValue(resource) }
        loginRepository.login("timo@tim.be", "test12345").subscribe { resource -> getLoginLiveData().postValue(resource) }
    }


    fun getLoginLiveData() = loginLiveData

}