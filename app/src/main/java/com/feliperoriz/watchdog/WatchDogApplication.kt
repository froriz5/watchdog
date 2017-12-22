package com.feliperoriz.watchdog

import android.app.Application
import com.feliperoriz.watchdog.di.ApplicationComponent
import com.feliperoriz.watchdog.di.DaggerApplicationComponent
import timber.log.Timber.*

/**
 * Created by feliperoriz on 12/21/17.
 */
class WatchDogApplication : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            plant(DebugTree())
        }
    }
}