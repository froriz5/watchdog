package com.feliperoriz.watchdog.di

import com.feliperoriz.watchdog.ui.MainActivity
import dagger.Component
import javax.inject.Singleton


/**
 * Created by Felipe Roriz on 12/21/17.
 */
@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface ApplicationComponent {

    fun inject(target: MainActivity)
}