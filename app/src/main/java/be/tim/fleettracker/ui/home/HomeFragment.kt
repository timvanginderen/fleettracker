package be.tim.fleettracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import be.tim.fleettracker.R
import be.tim.fleettracker.ui.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    private val TAG = HomeFragment::class.qualifiedName

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        initialiseViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (homeViewModel.isLoggedIn()) {
            // TODO: 24-Nov-20 setup data binding
        } else {
            // Go to login screen
            val actionToLogin = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
            Navigation.findNavController(view).navigate(actionToLogin)
        }
    }

    private fun initialiseViewModel() {
        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }
}