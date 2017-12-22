package com.feliperoriz.watchdog.ui

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.feliperoriz.watchdog.MainViewModel
import com.feliperoriz.watchdog.R
import com.feliperoriz.watchdog.WatchDogApplication
import com.feliperoriz.watchdog.data.MemberDTO
import com.feliperoriz.watchdog.di.ApplicationComponent
import com.feliperoriz.watchdog.network.CongressApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
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

        val adapter = MainAdapter(this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
        recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

//        api.getMembersFromHouse("IL")
        api.getMembersFromSenate("IL")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { members, throwable ->
                            if (throwable != null) {
                                Timber.e(throwable, "Failed to fetch congress members")
                            } else {
                                val democrats = members?.results?.filterNot { member -> member.party == MemberDTO.Party.REPUBLICAN } ?: ArrayList()
                                val republicans = members?.results?.filterNot { member -> member.party ==  MemberDTO.Party.DEMOCRAT } ?: ArrayList()

                                adapter.members = members?.results?: members.results
                                adapter.notifyDataSetChanged()

                                Timber.d("Democrats:\n%s", democrats)
                                Timber.d("Republicans:\n%s", republicans)
                            }
                        })

    }
}
