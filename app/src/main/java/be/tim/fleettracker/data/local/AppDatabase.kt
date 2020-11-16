package be.tim.fleettracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import be.tim.fleettracker.data.local.dao.LocationDao
import be.tim.fleettracker.data.local.entity.LocationEntity

@Database(entities = [LocationEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao() : LocationDao
}