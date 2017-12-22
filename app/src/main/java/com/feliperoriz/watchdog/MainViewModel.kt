package com.feliperoriz.watchdog

import android.arch.lifecycle.ViewModel
import timber.log.Timber

/**
 * Created by feliperoriz on 12/21/17.
 */
class MainViewModel : ViewModel() {

    fun test() = Timber.d("Testing!!!")
}