package be.tim.fleettracker.ui.locations

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.tim.fleettracker.R
import be.tim.fleettracker.databinding.LocationsFragBinding
import be.tim.fleettracker.ui.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class LocationListFragment : BaseFragment() {

    private val TAG = LocationListFragment::class.qualifiedName

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var locationListViewModel: LocationListViewModel

    private lateinit var locationsFragBinding: LocationsFragBinding
    private lateinit var rvLocations : RecyclerView

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
        return inflater.inflate(R.layout.locations_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = LocationsFragBinding.bind(view)
        locationsFragBinding = binding

        setupRecyclerView()
    }


    private fun initialiseViewModel() {
        locationListViewModel = ViewModelProviders.of(this, viewModelFactory).get(
            LocationListViewModel::class.java)
        locationListViewModel.getLocationsLiveData().observe(this, Observer { resource ->
            if (resource!!.isLoading) {
                // TODO: 14-Nov-20 implement progress indicator
            } else if (resource.data != null) {
                if (resource.data.isEmpty()) {
                    Log.d(TAG, "Get todos success with empty list")
                } else {
                    // update list
                    Log.d(TAG, "Get todos success: loaded ${resource.data.size} items")

                    val adapter = LocationsAdapter(resource.data)
                    rvLocations.adapter = adapter
                }
            } else {
                // TODO: 14-Nov-20 handle error
                Log.e(TAG, "Get todos error with msg: ${resource.message}")
            }

        })
        locationListViewModel.loadMore()
    }

    private fun setupRecyclerView() {
        rvLocations = locationsFragBinding.rvLocations
        rvLocations.layoutManager = LinearLayoutManager(context)

        val adapter = LocationsAdapter(emptyList())
        rvLocations.adapter = adapter
    }
}
