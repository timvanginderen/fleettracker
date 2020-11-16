package be.tim.fleettracker.ui.locations

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import be.tim.fleettracker.data.local.entity.LocationEntity
import be.tim.fleettracker.databinding.LocationItemBinding

// If your layout file is something_awesome.xml then your binding class will be SomethingAwesomeBinding
// Since our layout file is todo_item.xml, our auto generated binding class is TodoItemBinding
class LocationsAdapter(private val locations: List<LocationEntity>) : RecyclerView.Adapter<LocationsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LocationItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = locations.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(locations[position])

    inner class ViewHolder(val binding: LocationItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LocationEntity) {
            binding.location = item
            binding.executePendingBindings()
        }
    }
}