package be.tim.fleettracker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import be.tim.fleettracker.data.local.entity.LocationEntity

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocations(movies: List<LocationEntity>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(movie: LocationEntity): Long

    @Query("SELECT * FROM LocationEntity where id = :id")
    fun getLocationById(id: Long?): LocationEntity

    @Query("SELECT * FROM LocationEntity")
    fun getLocations(): List<LocationEntity>
}