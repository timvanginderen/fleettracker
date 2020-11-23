package be.tim.fleettracker.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.tim.fleettracker.data.Resource
import be.tim.fleettracker.data.local.entity.LocationEntity
import be.tim.fleettracker.data.remote.ApiService
import be.tim.fleettracker.data.remote.LoginResponse
import be.tim.fleettracker.data.repository.LocationRepository
import be.tim.fleettracker.data.repository.LoginRepository
import io.reactivex.observers.ResourceObserver
import javax.inject.Inject

class LoginViewModel  @Inject constructor(apiService: ApiService) : ViewModel() {

    private val TAG = LoginViewModel::class.qualifiedName

    private val loginRepository: LoginRepository = LoginRepository(apiService)

    private val loginLiveData = MutableLiveData<Resource<LoginResponse>>()


    fun login() {
//        locationRepository.loadLocations()
//                .subscribe { resource -> getLocationsLiveData().postValue(resource) }

//        var disposable = loginRepository.login().subscribe { resource -> getLoginLiveData().postValue(resource) }

        loginRepository.login().subscribe(
                { value -> Log.d(TAG, "value: $value") },
                { error -> Log.e(TAG,"error: $error") }
        )

//        val resourceObserver = object : ResourceObserver<Resource<LoginResponse>>() {
//            override fun onNext(t: Resource<LoginResponse>) {
//                getLoginLiveData().postValue(t)
//            }
//
//            override fun onError(t: Throwable) {
//                Log.e(TAG, t.message)
//            }
//
//            override fun onComplete() {
//            }
//        }
//
////        loginRepository.login().subscribe(resourceObserver)
//        loginRepository.login().doOnError { t: Throwable? -> Log.d(TAG, t!!.message.toString()) }


    }


    fun getLoginLiveData() = loginLiveData

}