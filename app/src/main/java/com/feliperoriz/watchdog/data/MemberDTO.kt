package com.feliperoriz.watchdog.data

import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import com.feliperoriz.watchdog.R
import com.squareup.moshi.Json

data class MemberDTO (
        val id: String,
        val name: String,
        val gender: String,
        val seniority: String,
        val party: Party,
        val role: String,
        @Json(name = "first_name") val firstName: String,
        @Json(name = "middle_name") val middleName: String?,
        @Json(name = "last_name") val lastName: String,
        @Json(name = "next_election") val nextElection: String,
        @Json(name = "twitter_id") val twitterId: String?
) {
    enum class Party(val initial: String, val title: String, @ColorRes val color: Int) {
        DEMOCRAT("D", "Democrat", R.color.democrat),
        REPUBLICAN("R", "Republican", R.color.republican),
        INDEPENDENT("I", "Independent", R.color.independent);

        companion object {
            fun fromString(initial: String): Party {
                val parties = Party.values()
                val party = parties.firstOrNull { party -> party.initial == initial }
                if (party == null) {
                    return INDEPENDENT
                }
                return party
            }
        }
    }
}