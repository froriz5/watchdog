package com.feliperoriz.watchdog

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.feliperoriz.watchdog.data.MembersDTO
import com.feliperoriz.watchdog.di.ApplicationComponent
import com.feliperoriz.watchdog.network.CongressApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiConsumer
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val appComponent: ApplicationComponent by lazy {
        (application as WatchDogApplication)
                .applicationComponent
    }

    private lateinit var viewModel: MainViewModel
    @Inject lateinit var api: CongressApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent.inject(this)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.test()

        api.getMembers("IL")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ members, throwable ->
                    if (throwable != null) {
                        Timber.e(throwable, "Failed to fetch congress members")
                    } else {
                        val democrats = members?.results?.filterNot { member -> member.party == "R" }
                        val republicans = members?.results?.filterNot { member -> member.party == "D" }
                        Timber.d("Democrats:\n%s", democrats)
                        Timber.d("Republicans:\n%s", republicans)
                } })

    }
}
