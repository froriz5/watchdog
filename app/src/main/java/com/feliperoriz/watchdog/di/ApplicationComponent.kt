package com.feliperoriz.watchdog.di

import android.app.Application
import com.feliperoriz.watchdog.MainActivity
import dagger.Component
import javax.inject.Singleton
import dagger.BindsInstance



/**
 * Created by Felipe Roriz on 12/21/17.
 */
@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface ApplicationComponent {

    fun inject(target: MainActivity)
}