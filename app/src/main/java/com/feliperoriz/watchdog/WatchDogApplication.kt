package com.feliperoriz.watchdog

import android.app.Application
import timber.log.Timber.*

/**
 * Created by feliperoriz on 12/21/17.
 */
class WatchDogApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            plant(DebugTree())
        }
    }
}