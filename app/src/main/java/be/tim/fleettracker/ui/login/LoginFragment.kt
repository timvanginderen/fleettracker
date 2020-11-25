package be.tim.fleettracker.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import be.tim.fleettracker.R
import be.tim.fleettracker.databinding.LoginFragBinding
import be.tim.fleettracker.ui.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    private val TAG = LoginFragment::class.qualifiedName

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginFragBinding: LoginFragBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        initialiseViewModel()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.login_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = LoginFragBinding.bind(view)
        loginFragBinding = binding
        loginViewModel.email = "timo@tim.be"
        loginViewModel.password = "test12345"
        loginFragBinding.viewmodel = loginViewModel

        navController = Navigation.findNavController(view)
    }

    private fun initialiseViewModel() {
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(
                LoginViewModel::class.java)
        loginViewModel.getLoginLiveData().observe(this, Observer { resource ->
            if (resource!!.isLoading) {
                // TODO: implement progress indicator
            } else if (resource.data != null) {
                if (resource.data.token != null) {
                    Log.d(TAG, "Login success")
                    navController.navigateUp()
                } else if (resource.message != null) {
                    val errorMessage = "Login error with msg: ${resource.message}"
                    showError(errorMessage)
                } else {
                    showError("Unknown error")
                }
            }
        })
    }

    private fun showError(errorMessage: String) {
        Log.e(TAG, errorMessage)
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }

}