package com.feliperoriz.watchdog.network

import com.feliperoriz.watchdog.data.MembersDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by feliperoriz on 12/21/17.
 */
interface CongressApi {

    @GET("members/house/{state}/current.json")
    fun getMembers(@Path("state") state: String): Single<MembersDTO>
}