package be.tim.fleettracker.di.module

import android.app.Application
import androidx.room.Room
import be.tim.fleettracker.data.local.AppDatabase
import be.tim.fleettracker.data.local.dao.LocationDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
                application, AppDatabase::class.java, "fleettracker.db")
                .allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    internal fun provideLocationDao(appDatabase: AppDatabase): LocationDao {
        return appDatabase.locationDao()
    }
}