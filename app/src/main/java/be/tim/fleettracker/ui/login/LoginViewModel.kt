package be.tim.fleettracker.ui.login

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.tim.fleettracker.data.Resource
import be.tim.fleettracker.data.remote.ApiService
import be.tim.fleettracker.data.remote.LoginResponse
import be.tim.fleettracker.data.repository.LoginRepository
import be.tim.fleettracker.prefs.AuthPrefManager
import javax.inject.Inject

class LoginViewModel  @Inject constructor(apiService: ApiService, authPrefManager: AuthPrefManager) : ViewModel() {

    private val loginRepository: LoginRepository = LoginRepository(apiService, authPrefManager)
    private val loginLiveData = MutableLiveData<Resource<LoginResponse>>()

    lateinit var email: String
    lateinit var password: String

    var isLoadingLogin: ObservableBoolean = ObservableBoolean(false)

    fun getLoginLiveData() = loginLiveData

    fun login() {
        loginRepository.login(email, password).subscribe {
            resource -> getLoginLiveData().postValue(resource)
        }
    }
}