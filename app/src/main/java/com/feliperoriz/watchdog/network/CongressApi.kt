package com.feliperoriz.watchdog.network

import com.feliperoriz.watchdog.data.MembersDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Pro Publica Congress API
 */
interface CongressApi {

    @GET("members/house/{state}/current.json")
    fun getMembersFromHouse(@Path("state") state: String): Single<MembersDTO>

    @GET("members/senate/{state}/current.json")
    fun getMembersFromSenate(@Path("state") state: String): Single<MembersDTO>
}