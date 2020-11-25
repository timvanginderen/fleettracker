package be.tim.fleettracker.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import be.tim.fleettracker.R
import be.tim.fleettracker.databinding.LocationsFragBinding
import be.tim.fleettracker.databinding.LoginFragBinding
import be.tim.fleettracker.ui.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    private val TAG = LoginFragment::class.qualifiedName

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var loginViewModel: LoginViewModel

    private lateinit var loginFragBinding: LoginFragBinding

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
        loginFragBinding.viewmodel = loginViewModel
    }

    private fun initialiseViewModel() {
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(
                LoginViewModel::class.java)
        loginViewModel.getLoginLiveData().observe(this, Observer { resource ->
            if (resource!!.isLoading) {
                // TODO: implement progress indicator
            } else if (resource.data != null) {
                Log.d(TAG, "Login success")

            } else {
                // TODO: 14-Nov-20 handle error
                Log.e(TAG, "Login error with msg: ${resource.message}")
            }

        })
    }

}